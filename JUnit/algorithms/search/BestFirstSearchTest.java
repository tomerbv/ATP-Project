package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    @Test
    void searchbestfirst() {
        MyMazeGenerator generator = new MyMazeGenerator();
        for (int rows = 2; rows <= 50; rows++) {
            for (int columns = 2; columns <= 50; columns++) {
                Maze maze = generator.generate(rows, columns);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                BestFirstSearch bfsalgo = new BestFirstSearch();
                Solution solution = bfsalgo.solve(searchableMaze);
                if (solution == null) {
                    System.out.println("didnt solve");
                    maze.superprint();
                }
            }
        }
        Maze maze1 = generator.generate(0, 0);
        SearchableMaze searchableMaze = new SearchableMaze(maze1);
        BestFirstSearch bfsalgo = new BestFirstSearch();
        Solution solution = bfsalgo.solve(searchableMaze);
        if (solution == null)
            System.out.println("could not search in null maze");



    }

}