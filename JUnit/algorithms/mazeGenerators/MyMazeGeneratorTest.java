package algorithms.mazeGenerators;

import algorithms.maze3D.MyMaze3DGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMazeGeneratorTest {

    @Test
    void generate() throws Exception {
        try{
            MyMazeGenerator generator = new MyMazeGenerator();
            for (int rows=2; rows <= 50; rows++){
                for (int columns=2; columns <= 50; columns++){
                    if((generator.generate( rows, columns)) == null){
                        System.out.println("Bad Test");
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}