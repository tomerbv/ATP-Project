package algorithms.search;

public abstract class AState {
    double cost;

    public AState(){
        this.cost = 0;
    }
    public AState(double cost){
        this.cost = cost;
    }
}