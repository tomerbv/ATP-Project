package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

class BreadthFirstSearchTest {
    @Test
    void searchbfs() {
        MyMazeGenerator generator = new MyMazeGenerator();
        for (int rows = 2; rows <= 20; rows++) {
            for (int columns = 2; columns <= 20; columns++) {
                Maze maze = generator.generate(rows, columns);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                BreadthFirstSearch bfsalgo = new BestFirstSearch();
                Solution solution = bfsalgo.solve(searchableMaze);
                if(solution==null) {
                    System.out.println("didnt solve");
                    maze.superprint();
                }
            }
        }
    }
}