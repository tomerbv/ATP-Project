package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

class DepthFirstSearchTest {
    @Test
    void searchdfs() throws Exception {
        MyMazeGenerator generator = new MyMazeGenerator();
        for (int rows = 2; rows <= 50; rows++) {
            for (int columns = 2; columns <= 50; columns++) {
                Maze maze = generator.generate(rows, columns);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                DepthFirstSearch bfsalgo = new DepthFirstSearch();
                Solution solution = bfsalgo.solve(searchableMaze);
                if(solution==null) {
                    System.out.println("didnt solve");
                    maze.superprint();
                }
            }
        }
    }

}