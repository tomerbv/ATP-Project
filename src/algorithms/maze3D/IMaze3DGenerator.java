package algorithms.maze3D;

/**
 * Interface IMaze3DGenerator for maze generating classes.
 */
public interface IMaze3DGenerator {
    /**
     * @param depth Determines dimension of depth for the created maze
     * @param row - Determines dimension of rows for the created maze
     * @param column - Determines dimension of columns for the created maze
     * @return Maze - The maze generated
     */
    Maze3D generate(int depth, int row, int column) throws Exception;

    /**
     * @param depth dimension of depth for the generated maze we measure
     * @param row dimension of rows for the generated maze we measure
     * @param column dimension of columns for the generated maze we measure
     * @return long - The time measured.
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception;
}
