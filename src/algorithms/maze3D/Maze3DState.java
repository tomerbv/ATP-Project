package algorithms.maze3D;

import algorithms.search.AState;


/**
 *  class that represents a state in a Maze3D
 */
public class Maze3DState extends AState {
    Position3D position;

    /**
     * @param position The position of the current state.
     * default constructor that initiates cost to.
     */
    public Maze3DState(Position3D position)throws Exception {
        super();
        if(position == null)
            throw new Exception("Null Argument");
        this.position = position;
    }

    /** Other constructor that receives a cost and initiates it.
     * @param position The position of the current state.
     * @param cost the cost accumulated to that state from the start
     */
    public Maze3DState(Position3D position, double cost)throws Exception {
        super();
        if(position == null)
            throw new Exception("Null Argument");
        this.position = position;
    }

    /** Position3D Getter
     * @return Position3D the Position of the current state.
     */
    public Position3D getPosition(){
        return this.position;
    }

    /**
     * @param o other object to compare to
     * @return true if o is equal to the current mazestate
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Maze3DState)) return false;
        Maze3DState mazeState = (Maze3DState) o;
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
