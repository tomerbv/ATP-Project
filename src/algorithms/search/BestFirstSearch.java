package algorithms.search;

import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{
    public BestFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if(!(domain instanceof SearchableMaze))
            return null;
        PriorityQueue<AState> Open = new PriorityQueue<AState>();
        HashSet<AState> Closed = new HashSet<AState>();
        Open.add(domain.getStartState());

    }



}
