package test;

import algorithms.maze3D.*;

public class RunMaze3DGenerator {
    public static void main(String[] args) throws Exception {
        testMaze3DGenerator(new MyMaze3DGenerator());
    }
    private static void testMaze3DGenerator(IMaze3DGenerator mazeGenerator) throws Exception {
        //prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(10/*depth*/,100/*rows*/,100/*columns*/)));
// generate another maze
        Maze3D maze = mazeGenerator.generate(5/*depth*/,10/*rows*/, 10/*columns*/);
// prints the maze
        maze.print();
// get the maze entrance
        Position3D startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
