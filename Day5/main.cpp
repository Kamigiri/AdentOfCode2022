#include <fstream>
#include <filesystem>
#include <iostream>
#include <string>
#include <vector>
#include <regex>
#include "Crate.h"
#include "Order.h"

using namespace std;

vector<aoc::Crate> readCrateInput(vector<string> rawDataCrates, string rawDataCratesGroup, int rawDataCratesLength) {
    vector<aoc::Crate> crates;
    for (int i = 0; i < rawDataCratesLength /4; i++) {
        aoc::Crate newCrate = aoc::Crate(i +1);
        crates.push_back(newCrate);
    }

    for (int i = 0; i < rawDataCrates.size(); i++) {
        string line = rawDataCrates[i];
        for (int j = 0; j < line.length(); j++) {
            char currentChar = line[j];
            if (regex_match(string(1, currentChar), regex("[A-Z]"))) {
                char idChar = rawDataCratesGroup[j];
                int crateId = std::atoi(&idChar);
                crates[crateId -1].addToFrontOfCrate(currentChar);
            }
        }
    }
    return crates;
}

vector<aoc::Order>  readOrderInput(vector<string> rawDataOrder) {
    vector<aoc::Order> orders;

    for (int i = 0; i < rawDataOrder.size(); i++) {
        string line = rawDataOrder[i];
        aoc::Order newOrder = aoc::Order(line);
        orders.push_back(newOrder);
    };
    return orders;
};

void createSolution(vector<aoc::Order> orders, vector<aoc::Crate> crates ) {
    vector<aoc::Crate> cratesMover9000 = crates;
    vector<aoc::Crate> cratesMover9001 = crates;
    for (int i = 0; i < orders.size(); i++) {

        aoc::Order currentOrder = orders[i];
        aoc::Crate& start9000 = cratesMover9000[currentOrder.getStartCrate() - 1];
        aoc::Crate& destination9000 = cratesMover9000[currentOrder.getDestinationCrate() - 1];
        aoc::Crate& start9001 = cratesMover9001[currentOrder.getStartCrate() - 1];
        aoc::Crate& destination9001 = cratesMover9001[currentOrder.getDestinationCrate() - 1];
        vector<char> tempCrates;
        for (int j = 0; j < currentOrder.getAmountToBeMoved() ; ++j) {
            destination9000.addToBackOfCrate(start9000.getLastElement());
            tempCrates.insert(tempCrates.begin(), start9001.getLastElement());
        }

        for (int j = 0; j < tempCrates.size(); ++j) {
            destination9001.addToBackOfCrate(tempCrates[j]);
        }
    };
    for (int i = 0; i < cratesMover9000.size(); ++i) {
        std::cout << cratesMover9000[i].getLastElement();
    }
    std::cout << endl;
    for (int i = 0; i < cratesMover9001.size(); ++i) {
        std::cout << cratesMover9001[i].getLastElement();
    }

}

int main()
{
    vector<string> rawDataCrates;
    string rawDataCratesGroup;
    vector<string> rawDataOrder;
    int rawDataCratesLength = 0;
    int rawDataOrderLength = 0;
    bool isSectionedOff = false;
    filesystem::path sourcePath(__FILE__);
    filesystem::path sourceDirectory = sourcePath.parent_path();
    filesystem::path inputPath = sourceDirectory / "input.txt";
    ifstream inputFile(inputPath);


    // Read the input from the file
    string line;
    while (getline(inputFile, line))
    {
        if(line.empty()) {
            isSectionedOff = true;
            continue;
        };
        if (!isSectionedOff) {
            if(rawDataCratesLength == 0) {
                rawDataCratesLength = line.length() + 1;
            };
            if(line.substr(1, 1) != "1") {
                rawDataCrates.push_back(line);
            } else {
                rawDataCratesGroup = line;
            }

        } else {
            if(rawDataOrderLength == 0) {
                rawDataOrderLength = line.length();
            };
            rawDataOrder.push_back(line);
        }
    }

    // Close the input file
    inputFile.close();
    vector<aoc::Crate> crates = readCrateInput(rawDataCrates,rawDataCratesGroup, rawDataCratesLength);
    vector<aoc::Order> orders = readOrderInput(rawDataOrder);
    createSolution(orders,crates);

    return 0;
};
