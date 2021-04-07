package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (!(domain instanceof ISearchable))
            return null;
        Stack<AState> Open = new Stack<AState>();
        HashSet<AState> Closed = new HashSet<AState>();
        ArrayList<AState> Successors = new ArrayList<AState>();

        Open.push(domain.getStartState());
        AState currNode;
        while (!Open.isEmpty()) {
            currNode = Open.pop();
            if (currNode.equals(domain.getGoalState()))
                return new Solution(currNode);
            Successors = domain.getAllSuccessors(currNode);
            for (int i = 0; i < Successors.size(); i++){
                AState successor = Successors.get(i);
                if (!Closed.contains(successor)) {
                    if (!Open.contains(successor)) {
                        successor.setCameFrom(currNode);
                        successor.setCost(currNode.getCost() + 1);
                        Open.push(currNode);
                        Open.push(successor);
                        this.NodesEvaluated ++;
                        currNode = successor;
                        Successors = domain.getAllSuccessors(currNode);
                        i = 0;
                    }
                }
            }
            Closed.add(currNode);
        }
        return null;

    }
}
