package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void toByteArray() {
        MyMazeGenerator generator = new MyMazeGenerator();
        Maze maze = generator.generate(10,10);
        maze.superprint();
        System.out.println("\n****************************\n");
        byte[] bytearray = maze.toByteArray();
        Maze recoveredmaze = new Maze(bytearray);
        recoveredmaze.superprint();


    }
}