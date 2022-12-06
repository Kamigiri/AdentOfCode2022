package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func getData(filename string) []string {
	file, err := os.Open(filename)
	if err != nil {
		fmt.Println(err)
		return nil
	}
	reader := bufio.NewReader(file)
	var lines []string

	for {
		line, err := reader.ReadString('\n')
		if err == io.EOF {
			break
		} else if err != nil {
			fmt.Println(err)
			return nil
		}
		lines = append(lines, line)
	}
	return lines
}

func checkForDuplicates(testCase []string) bool {
	seen := make(map[string]bool)
	isNotDuplicate := true
	for _, item := range testCase {
		if seen[item] {
			isNotDuplicate = false
			break
		}
		seen[item] = true
	}
	return isNotDuplicate
}

func createTestCase(amount int, start int, data string) []string {
	var testCase []string
	for i := 1; i <= amount; i++ {
		testCase = append(testCase, string(data[start+i]))
	}
	return testCase
}

func createSolution(input string, amount int) {
	for i := range input {
		if i+amount-1 > len(input) {
			break
		}
		var testcase = createTestCase(amount, i, input)
		var isFound = checkForDuplicates(testcase)
		if isFound {
			fmt.Println(i + 1 + amount)
			break
		}
	}
}

func main() {
	var lines = getData("input.txt")
	for _, line := range lines {
		createSolution(line, 4)
		createSolution(line, 14)
	}

}
