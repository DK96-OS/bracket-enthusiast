# Bracket Enthusiast
An Object Oriented Software Package of Bracket Search and Analysis Algorithms.

## Features
- Process Any `BracketType`
- Indexes open and close brackets separately
- Validates that brackets are balanced
- Easily determine the locations of bracket pairs

## Optimizations
- Scans input once to obtain all bracket locations
- Uses bracket logic on the indices to deduce nesting and validity
- Exports Bracket Pair Information in an Integer Array

### Bracket Pair Array Optimization
The method `getBracketPairs()` utilizes an O(N) algorithm that builds an integer array of length N, where N is the total number of brackets.
The array contains values alternating between open and close bracket locations, where all even indices are opening brackets, and all odd indices are closing brackets.
This structure enables O(N) because every opening bracket index can be inserted into the result array in the same sorted order.
The closing bracket indices are carefully inserted next to the correct unmatched opening index.
