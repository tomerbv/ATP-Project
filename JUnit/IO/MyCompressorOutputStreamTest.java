package IO;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyCompressorOutputStreamTest {

    @Test
    void write() {
        String mazeFileName = "savedMaze.maze";
        for (int i = 2; i < 30; i++) {
            for (int j = 2; j < 30; j++) {
                AMazeGenerator mazeGenerator = new MyMazeGenerator();
                Maze maze = null; //Generate new maze
                try {
                    maze = mazeGenerator.generate(i, j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
// save maze to a file
                    OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                    out.write(maze.toByteArray());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte savedMazeBytes[] = new byte[0];
                try {
//read maze from file

                    InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                    savedMazeBytes = new byte[maze.toByteArray().length];
                    in.read(savedMazeBytes);
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Maze loadedMaze = new Maze(savedMazeBytes);
                boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
                if(!areMazesEquals) {
                    System.out.println("Failed");
                    loadedMaze.superprint();
                }
                if(!areMazesEquals)
                    System.out.println(String.format("Mazes equal: %s", areMazesEquals));
            }
        }
    }
}
