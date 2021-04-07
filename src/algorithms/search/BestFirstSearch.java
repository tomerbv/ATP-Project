package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    @Override
    public void AddSuccessor(AState curr, AState Successor){
        Successor.setCameFrom(curr);
        Successor.addCost(curr.getCost());
    }

}





