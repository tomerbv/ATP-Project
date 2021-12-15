# ATP-Project
Implementation Maze generator, a compressor and a maze solver for ATP course in SISE department of BGU University.

## General Information

In this project I implemented a few concepts:
- compression algorithm for mazes and search algorothms solutions.
- maze generator 
- maze solver

With that basic concept the project is able to maintain a basic client server interacion using sockets.
Thus, by simple requests a client can ask the server to generate or solve a maze of any size.

## Algorithms used
### Compression:
compressing each each byte representing a 1 (wall) or 0 (path) into a single bit makes an efficient way of comression of a maze class into bytes.

### Maze Generating:
You can choose which maze generating algorithm to use. Choose between:
- Simple Maze Generator - Randomly places walls to form a maze. It produces simple and not interesting mazes
- My Maze Generator - Uses Prim's Algorithm to generate interesting mazes with dead ends.

### Maze Solving:
You can choose which maze solving algorithm to use. Choose between:
- DFS - Depth First Search
- BFS - Breadth First Search
- Best First Search - huristic based search alorithm that calculates the cheapest path (a diagonal step costs more than a regular step)
