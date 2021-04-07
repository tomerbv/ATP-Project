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
    public Position getPosition(){
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MazeState)) return false;
        MazeState mazeState = (MazeState) o;
        return this.position.equals(mazeState.getPosition());
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }

    public String toString(){
        return this.position.toString();
    }
}
