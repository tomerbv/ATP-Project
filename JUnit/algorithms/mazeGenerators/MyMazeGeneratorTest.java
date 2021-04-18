package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;

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
        try{
            /* input test */
            MyMazeGenerator generator = new MyMazeGenerator();
            Maze maze1 = generator.generate(0, 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}