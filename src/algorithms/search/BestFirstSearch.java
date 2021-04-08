package algorithms.search;

/**
 * represents the bestfirst algorithm, this algorithm extends the breadfirst algorithm because it solves the maze almost the same
 * way except the way it adds its successors to the priority queue
 */
public class BestFirstSearch extends BreadthFirstSearch {
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





