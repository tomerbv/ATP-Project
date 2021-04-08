package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    @Override
    protected void AddSuccessor(AState curr, AState Successor){
        Successor.setCameFrom(curr);
        Successor.addCost(curr.getCost());
    }
}





