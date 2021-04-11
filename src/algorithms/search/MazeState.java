package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * a class that represents a state in a Maze
 */
public class MazeState extends AState{
    Position position;

    /**default constructor that initiates cost to 0
     * @param position The position of the current state.
     */
    public MazeState(Position position) throws Exception {
        super();
        if(position == null)
            throw new Exception("Null Argument");
        this.position = position;
    }

    /**other constructor that receives a cost and initiates it.
     * @param position The position of the current state.
     * @param cost the cost accumulated to that state from the start
     */
    public MazeState(Position position, double cost) throws Exception {
        super(cost);
        if(position == null)
            throw new Exception("Null Argument");
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
