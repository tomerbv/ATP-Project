package algorithms.mazeGenerators;
import java.lang.Math;

/**
 * SimpleMazeGenerator class generates a simple maze with at least 3 possible paths.
 */
public class SimpleMazeGenerator extends AMazeGenerator  {

    /**
     * @param rows    - Determines number of rows for the created maze .
     * @param columns - Determines number of columns for the created maze.
     * @return Maze - The maze generated.
     */
    public Maze generate(int rows, int columns) throws Exception {
        if(rows < 2 || columns < 2 )
            throw new Exception("Invalid Dimension Size");
        Position start = new Position(0,0);
        Position goal = new Position(rows - 1,columns - 1);
        Maze maze = FillMaze(rows,columns, start,goal, 1);
        double dir = 0.2;
        for(int k = 0; k < 3; k++){
            int i = 0;
            int j = 0;
            while(i != rows - 1 || j != columns - 1){
                maze.set(i,j,0);
                if( ((Math.random() > dir) && i < rows - 1) || j == columns - 1)
                    i++;
                else
                    j++;
            }
            dir += 0.3;
        }
        maze.set(rows - 1,columns - 1,0);
        return maze;
    }
}
