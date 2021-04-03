package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public abstract Maze generate(int rows, int columns);

    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long s = System.currentTimeMillis();
        generate(rows, columns);
        return (System.currentTimeMillis() - s);
    }

}
