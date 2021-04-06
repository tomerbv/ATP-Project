package algorithms.search;

import algorithms.mazeGenerators.Position;

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
}
