package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class MazeState extends AState{
    Position position;

    public MazeState(Position position){
        super();
        this.position = position;
    }
    public MazeState(Position position, double cost){
        super(cost);
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazeState = (MazeState) o;
        return Objects.equals(position, mazeState.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
