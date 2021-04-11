package algorithms.maze3D;

import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMaze3DGeneratorTest {
    @Test
    void generate() throws Exception {
        try{
            MyMaze3DGenerator generator = new MyMaze3DGenerator();
            for (int depth=2; depth <= 20; depth++) {
                for (int rows = 2; rows <= 20; rows++) {
                    for (int columns = 2; columns <= 20; columns++) {
                        if ((generator.generate(depth, rows, columns)) == null) {
                            System.out.println("Bad Test");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}