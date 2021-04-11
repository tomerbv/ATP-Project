package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {

    /**
     * @param depth Dimension of depth for the generated maze we measure
     * @param rows Dimension of rows for the generated maze we measure
     * @param columns Dimension of columns for the generated maze we measure
     * @return long - The time measured.
     */
    public long measureAlgorithmTimeMillis(int depth, int rows, int columns) throws Exception {
        long s = System.currentTimeMillis();
        generate(depth, rows, columns);
        return (System.currentTimeMillis() - s);
    }

}
