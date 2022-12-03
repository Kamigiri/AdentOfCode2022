import { readFileSync } from "fs";

class Main {
  createSolution(filename: string) {
    const dataSingle: string[][] = this.getData(filename);
    const dataTriplet: string[][] = this.getDataTriplet(filename);
    let prioritiesSingle: number = 0;
    let prioritiesTriplet: number = 0;
    this.getSharedCharacter(dataSingle).forEach((char) => {
      if (char.length) {
        prioritiesSingle += this.convertToPoints(char);
      }
    });
    this.getSharedCharacterTriplet(dataTriplet).forEach((char) => {
      if (char.length) {
        prioritiesTriplet += this.convertToPoints(char);
      }
    });
    console.log(prioritiesSingle, prioritiesTriplet);
  }

  private convertToPoints(input: string): number {
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

  private getSharedCharacter(data: string[][]) {
    const result: string[] = [];
    data.forEach((items) => {
      if (items[0].length && items[1].length) {
        items[0].split("").every((char) => {
          let isNotFound: boolean = true;
          if (items[1].includes(char)) {
            result.push(char);
            isNotFound = false;
          }
          return isNotFound;
        });
      }
    });
    return result;
  }
  private getSharedCharacterTriplet(data: string[][]) {
    const result: string[] = [];
    data.forEach((items) => {
      if (items[0].length && items[1].length && items[2].length) {
        items[0].split("").every((char) => {
          let isNotFound: boolean = true;
          if (items[1].includes(char) && items[2].includes(char)) {
            result.push(char);
            isNotFound = false;
          }
          return isNotFound;
        });
      }
    });
    return result;
  }

  private getData(filename: string): string[][] {
    const file: string = readFileSync(filename, "utf-8");

    const lines: string[] = file.split(/\r?\n/);

    const linePairs: string[][] = [];

    lines.forEach((line) => {
      const pair: string[] = [
        line.substring(0, line.length / 2),
        line.substring(line.length / 2),
      ];
      linePairs.push(pair);
    });
    return linePairs;
  }
  private getDataTriplet(filename: string): string[][] {
    const file: string = readFileSync(filename, "utf-8");

    const lines: string[] = file.split(/\r?\n/);
    let triplet: string[] = [];
    const linePairs: string[][] = [];

    lines.forEach((line) => {
      triplet.push(line);
      if (triplet.length == 3) {
        linePairs.push(triplet);
        triplet = [];
      }
    });
    return linePairs;
  }
}

const main = new Main();
main.createSolution("./input.txt");
