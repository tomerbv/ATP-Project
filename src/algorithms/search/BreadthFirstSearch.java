package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (!(domain instanceof ISearchable))
            return null;
        PriorityQueue<AState> Open = new PriorityQueue<AState>();
        HashSet<AState> Closed = new HashSet<AState>();
        ArrayList<AState> Successors = new ArrayList<AState>();
        Open.add(domain.getStartState());
        AState currNode;
        while (!Open.isEmpty()) {
            currNode = Open.poll();
            if (currNode.equals(domain.getGoalState()))
                return new Solution(currNode);
            Successors = domain.getAllSuccessors(currNode);
            for (AState successor : Successors) {
                if (!Closed.contains(successor)) {
                    if (!Open.contains(successor)) {
                        AddSuccessor(currNode, successor);
                        Open.add(successor);
                        this.NodesEvaluated ++;
                    }
                    Closed.add(currNode);
                }
            }

        }
        return null;
    }

    public void AddSuccessor(AState curr, AState Successor){
        Successor.setCameFrom(curr);
        Successor.setCost(curr.getCost() + 1);
    }

}
