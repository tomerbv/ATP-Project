package algorithms.search;

public abstract class AState implements Comparable<AState>{
    double cost;
    AState CameFrom;

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
