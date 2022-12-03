import { readFileSync } from "fs";

function createSolution(filename: string) {
  const dataSingle: string[][] = getData(filename);
  const dataTriplet: string[][] = getData(filename, true);
  let prioritiesSingle: number = 0;
  let prioritiesTriplet: number = 0;
  getSharedCharacter(dataSingle).forEach((char) => {
    if (char.length) {
      prioritiesSingle += convertToPoints(char);
    }
  });
  getSharedCharacter(dataTriplet, true).forEach((char) => {
    if (char.length) {
      prioritiesTriplet += convertToPoints(char);
    }
  });
  console.log(prioritiesSingle, prioritiesTriplet);
}

function convertToPoints(input: string): number {
  let code: number = Number(input.charCodeAt(0));
  let result: number = 0;
  // ASCII lowercase letter start with a === 97, so we reduce all lowercase letter with 96 to get 1-26
  if (code >= 97) {
    result = code - 96;
  } else {
    // ASCII Uppercase letter start with A === 65, so we reduce all lowercase letter with 38 to get 27-52
    result = code - 38;
  }
  return result;
}

function getSharedCharacter(data: string[][], isTriplet: boolean = false) {
  const result: string[] = [];
  data.forEach((items) => {
    items[0].split("").every((char) => {
      let isNotFound: boolean = true;
      if (isTriplet) {
        if (items[1].includes(char) && items[2].includes(char)) {
          result.push(char);
          isNotFound = false;
        }
      } else {
        if (items[1].includes(char)) {
          result.push(char);
          isNotFound = false;
        }
      }

      return isNotFound;
    });
  });
  return result;
}

function getData(filename: string, isTriplet: boolean = false): string[][] {
  const file: string = readFileSync(filename, "utf-8");
  const lines: string[] = file.split(/\r?\n/);
  let triplet: string[] = [];
  const result: string[][] = [];

  lines.forEach((line) => {
    if (isTriplet) {
      triplet.push(line);
      if (triplet.length == 3) {
        result.push(triplet);
        triplet = [];
      }
    } else {
      const pair: string[] = [
        line.substring(0, line.length / 2),
        line.substring(line.length / 2),
      ];
      result.push(pair);
    }
  });
  return result;
}

createSolution("./input.txt");
