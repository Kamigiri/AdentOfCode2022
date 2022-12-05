//
// Created by Kamigiri on 05/12/2022.
//

#ifndef DAY5_ORDER_H
#define DAY5_ORDER_H

#include <string>
#include <regex>

namespace aoc {
    using namespace std;

    class Order {
    public:
        Order(string rawDataOrder) : rawDataOrder_(rawDataOrder)
                                                              {
            string line = rawDataOrder_;
            smatch match;
            if (regex_search(line, match, amountOfCratesRegex_)) {
                string matchStr = match.str(1);
                amountToBeMoved_ = stoi(matchStr);
            };
            if (regex_search(line, match, startOfCratesRegex_)) {
                string matchStr = match.str(1);
                startCrate_ = stoi(matchStr);
            };
            if (regex_search(line, match, endOfCratesRegex_)) {
                string matchStr = match.str(1);
                destinationCrate_ = stoi(matchStr);
            };
        }

        int getAmountToBeMoved() {
            return amountToBeMoved_;
        };

        int getStartCrate() {
            return startCrate_;
        };

        int getDestinationCrate() {
            return destinationCrate_;
        };

        string getRawOrder() {
            return rawDataOrder_;
        };

    private:
        string rawDataOrder_;
        int amountToBeMoved_;
        int startCrate_;
        int destinationCrate_;
        regex amountOfCratesRegex_ = regex("move (\\d+) from");
        regex startOfCratesRegex_ = regex("from (\\d+) to");
        regex endOfCratesRegex_ = regex("to (\\d+)");
    };

} // aoc

#endif //DAY5_ORDER_H
