#!/usr/bin/python3
from pathlib import Path
import bisect


class Elve:
    _calories = []

    def __init__(self, calories):
        self._calories = calories

    def getMaxCalories(self):
        return sum(self._calories)


def getData(filename):
    elves = []
    with open(getFilePath(filename), 'r', encoding="utf-8") as f:
        content = f.readlines()
        items = []
        for line in content:
            if line.strip() == "":
                elves.append(Elve(items))
                items = []
            else:
                items.append(int(line))

    return elves


def getFilePath(filename):
    currentDir = Path(__file__).parent.resolve()
    return Path(currentDir) / filename


def main():
    elves = getData("input.txt")
    max = 0
    maxThree = 0
    maxCaloriesList = []
    for elve in elves:
        bisect.insort(maxCaloriesList, elve.getMaxCalories())
        if max <= elve.getMaxCalories():
            max = elve.getMaxCalories()
    maxThree = sum(maxCaloriesList[-3:])

    print(f"The most calories an elve is carrying is: {max}")
    print(
        f"The Amount of calories, the elves with the three most amount of calories is: {maxThree}")


if __name__ == "__main__":
    main()
