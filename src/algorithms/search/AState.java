package algorithms.search;

import java.io.Serializable;

/**Astate is abstract class that represents the situation
 *  the searching algorithm is currently in
 * field1: cost - the cost to reach the certain state
 * field2: camefrom- the state that the certain state came from
 */
public abstract class AState implements Comparable<AState>, Serializable {

    double cost;
    AState CameFrom;

    /** Constructor:
     * one default constructor that initiates cost to 0
     * second constructor that recieves a cost to start with
     */
    public AState(){
        this.cost = 0;
    }

    /** Second constructor that receives a cost to start with
     * @param cost the cost accumulated to that state from the start
     */
    public AState(double cost){
        this.cost = cost;
    }

    /** CameFrom Getter
     * @return State the current state came from.
     */
    public AState getCameFrom() {
        return CameFrom;
    }

    /** Cost Setter
     * @param state the current state
     */
    public void setCameFrom(AState state) {
        this.CameFrom = state;
    }

    /** Cost Getter
     * @return the cost accumulated to that state from the start
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost accumulated to that state from the start
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @param cost the cost accumulated to that state from the previous step
     */
    public void addCost(double cost) {
        this.cost += cost;
    }

    /**
     * @param other  the other state that we compare to find the cheaper step
     *
     * @return returns 1 if this state is more expensive 0 if they have the
     * same cost and -1 if other has a more expensive cost
     */
    @Override
    public int compareTo(AState other) {
        if (this.cost < other.cost) {
            return -1;
        }
        if (this.cost == other.cost) {
            return 0;
        }
        return 1;
    }

    /** equals method implemented by the class extending this class
     * @param o
     * @return
     */
    @Override
    public abstract boolean equals(Object o);

    /** hashCode method implemented by the class extending this class
     * @return int hashcode
     */
    @Override
    public abstract int hashCode();

    /** toString method implemented by the class extending this class
     * @return String
     */
    public abstract String toString();
}

