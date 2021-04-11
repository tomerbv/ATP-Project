package algorithms.mazeGenerators;

/**
 * EmptyMazeGenerator class generates an empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator  {
    /**
     * @param rows - Determines number of rows for the created maze
     * @param columns - Determines number of columns for the created maze
     * @return Maze - The maze generated
     */
    public Maze generate(int rows, int columns) throws Exception {
        if(rows < 2 || columns < 2 )
            throw new Exception("Invalid Dimension Size");
        Position start = new Position(0,0);
        Position goal = new Position(rows - 1,columns - 1);
        Maze maze = FillMaze(rows,columns, start,goal, 0);
        return maze;
    }
}
