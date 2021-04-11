package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * a class that represents a state in a Maze
 */
public class MazeState extends AState{
    Position position;

    /**
     * @param position represents the position in the maze we are in
     * default constructor that initiates cost to 0
     * other constructor that recieves a cost and initiates it to the mazestate field
     */
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

    /**
     * @param o other object to compare to
     * @return true if o is equal to the current mazestate
     */
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
