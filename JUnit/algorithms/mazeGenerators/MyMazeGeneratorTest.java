package algorithms.mazeGenerators;

import algorithms.maze3D.MyMaze3DGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMazeGeneratorTest {

    @Test
    void generate() {
        MyMazeGenerator generator = new MyMazeGenerator();
        for (int rows=2; rows <= 20; rows++){
            for (int columns=2; columns <= 20; columns++){
                if((generator.generate( rows, columns)) == null)
                    System.out.println("Bad Test");


            }
        }
    }
}