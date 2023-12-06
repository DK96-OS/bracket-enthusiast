# Bracket Enthusiast
Bracket Search and Analysis!

## Features
- Process a supported `BracketType` (single char)
- Indexes open and close brackets independently
- Validates that brackets are balanced
- Process Bracket Enthusiast data into a Tree of Bracket Nodes
- Search for a specific Node in the Bracket Tree

## Optimizations
- Scans input once to obtain all bracket locations
- Uses bracket logic on the indices to deduce nesting and validity
- Exports Bracket Pair Information in an Integer Array

### Bracket Pair Array Optimization
The method `getBracketPairs()` utilizes an O(N) algorithm that builds an integer array of length N, where N is the total number of brackets. The array contains values alternating between open and close bracket locations, where all even indices are opening brackets, and all odd indices are closing brackets.

This structure enables O(N) because every opening bracket index can be inserted into the result array in the same sorted order.
The closing bracket indices are carefully inserted next to the correct unmatched opening index.

### Bracket Tree Algorithms
`BracketNode` instances contain a resizable array for child nodes.

Searching for a Node uses a binary search algorithm. This is supported by ensuring that all nodes in the array are ordered.

Inserting a Node is done by searching for the smallest parent Node, and adding to the array of Child Nodes. The `addNode()` methods have package-private visibility to ensure that they are only used by the `BracketTreeBuilder`.

### Bracket Tree Builder
The `BracketTreeBuilder` class sources data from a `BracketEnthusiast`, ensuring that special conditions (including balanced brackets) are met. The builder uses the well-formatted output of `getBracketPairs()` to build the Tree one node at a time, and maintain a correct order of child nodes.
