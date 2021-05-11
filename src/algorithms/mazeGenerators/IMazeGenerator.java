package algorithms.mazeGenerators;

/**
 * Interface IMazeGenerator for maze generating classes.
 */
public interface IMazeGenerator {
    /**
     * @param rows - Determines number of rows for the created maze
     * @param columns - Determines number of columns for the created maze
     * @return Maze - The maze generated
     */
    Maze generate(int rows, int columns) ;

    /**
     * @param rows Number of rows for the generated maze we measure
     * @param columns Number of columns for the generated maze we measure
     * @return long - The time measured.
     */
    long measureAlgorithmTimeMillis(int rows, int columns) throws Exception;
}
