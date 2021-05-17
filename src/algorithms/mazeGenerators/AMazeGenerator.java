package algorithms.mazeGenerators;

/**
 *Abstract class for maze generators implements IMazeGenerator.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /** measures the time if
     * @param rows Number of rows for the generated maze we measure
     * @param columns Number of columns for the generated maze we measure
     * @return long - The time measured.
     */
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long s = System.currentTimeMillis();
        generate(rows, columns);
        return (System.currentTimeMillis() - s);
    }

    /** creates a maze and fills it with the desired int.
     * @param rows number of rows for the desired maze.
     * @param columns number of columns for the desired maze.
     * @param start start Position for the desired maze.
     * @param goal goal Position for the desired maze.
     * @param fill the int the defaultive maze will be constructed with.
     * @return Maze - a filled maze
     */
    protected Maze FillMaze(int rows, int columns, Position start, Position goal, int fill){
        try{
            int[][] map = new int[rows][columns];
            Maze maze = new Maze(start, goal, map);
            for (int i=0; i <= rows - 1; i++){
                for (int j=0; j <= columns - 1; j++){
                    maze.set(i,j,fill);
                }
            }
            return maze;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
