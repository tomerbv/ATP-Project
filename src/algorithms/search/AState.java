package algorithms.search;

/**Astate is abstract class that represents the situation
 *  the searching algorithm is currently in
 * field1: cost - the cost to reach the certain state
 * field2: camefrom- the state that the certain state came from
 */
public abstract class AState implements Comparable<AState>{

    double cost;
    AState CameFrom;

    /**constructor:
     * one default constructor that initiates cost to 0
     * second constructor that recieves a cost to start with
     *
     */
    public AState(){
        this.cost = 0;
    }
    public AState(double cost){
        this.cost = cost;
    }

    public AState getCameFrom() {
        return CameFrom;
    }

    public void setCameFrom(AState state) {
        this.CameFrom = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

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

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public abstract String toString();
}

