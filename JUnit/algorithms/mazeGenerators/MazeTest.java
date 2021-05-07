package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void toByteArray() {
        int a = 128;
        byte b = (byte) a;
        for (int i = 0; i < 20000; i++) {
            b = (byte) (b >> 1);
        }
        b = (byte) (a >> 2);
        System.out.println(b);
        b = (byte) (a >> 3);
        System.out.println(b);
        b = (byte) (a >> 4);
        System.out.println(b);
        b = (byte) (a & 0xFF);
        System.out.println(b & 0xFF);


        /*
        MyMazeGenerator generator = new MyMazeGenerator();
        Maze maze = generator.generate(10,10);
        maze.superprint();
        System.out.println("\n****************************\n");
        byte[] bytearray = maze.toByteArray();
        Maze recoveredmaze = new Maze(bytearray);
        recoveredmaze.superprint();
        */

    }
}