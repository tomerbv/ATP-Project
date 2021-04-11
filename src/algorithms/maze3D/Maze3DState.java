package algorithms.maze3D;

import algorithms.search.AState;


public class Maze3DState extends AState {
    Position3D position;

    /**
     * @param position represents the position in the maze we are in
     * default constructor that initiates cost to 0
     * other constructor that recieves a cost and initiates it to the mazestate field
     */
    public Maze3DState(Position3D position){
        super();
        this.position = position;
    }

    public Maze3DState(Position3D position, double cost){
        super(cost);
        this.position = position;
    }
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
