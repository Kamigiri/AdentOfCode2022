//
// Created by Kamigiri on 05/12/2022.
//

#ifndef DAY5_CRATE_H
#define DAY5_CRATE_H
#include <vector>
namespace aoc {


    class Crate
    {
    public:
        Crate(int id) : id_(id) {}

        int getId()
        {
            return id_;
        };

        char getLastElement()
        {
            char itemCopy = chars_.back();
            chars_.pop_back();
            return itemCopy;
        };

        void addToBackOfCrate(char item)
        {
            chars_.push_back(item);
        };

        void addToFrontOfCrate(char item)
        {
            chars_.insert(chars_.begin(), item);
        };

    private:
        std::vector<char> chars_;
        int id_;
    };

} // aoc

#endif //DAY5_CRATE_H
