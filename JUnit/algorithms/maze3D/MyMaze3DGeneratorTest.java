package algorithms.maze3D;

import algorithms.search.*;
import org.junit.jupiter.api.Test;

class MyMaze3DGeneratorTest {
    @Test
    void generate() throws Exception {
        try{
            MyMaze3DGenerator generator = new MyMaze3DGenerator();
            for (int depth=2; depth <= 20; depth++) {
                for (int rows = 2; rows <= 20; rows++) {
                    for (int columns = 2; columns <= 20; columns++) {
                        Maze3D maze3d = generator.generate(depth, rows, columns);
                        if (maze3d == null) {
                            System.out.println("Bad Test");
                        }
                        /** testing that the maze is solvable */
                        SearchableMaze3D searchableMaze3D = new SearchableMaze3D(maze3d);
                        BreadthFirstSearch bfsalgo = new BestFirstSearch();
                        Solution solution = bfsalgo.solve(searchableMaze3D);
                        if(solution==null) {
                            System.out.println("didnt solve");
                            maze3d.superprint();
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