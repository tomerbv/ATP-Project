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
        if (domain == null)
            return null;
        PriorityQueue<AState> Open = new PriorityQueue<AState>();
        HashSet<AState> Closed = new HashSet<AState>();
        ArrayList<AState> Successors = new ArrayList<AState>();
        Open.add(domain.getStartState());
        AState currNode;
        while (!Open.isEmpty()) {
            currNode = Open.poll();
            this.NodesEvaluated ++;
            if (currNode.equals(domain.getGoalState()))
                return new Solution(currNode);
            Successors = domain.getAllSuccessors(currNode);
            for (AState successor : Successors) {
                if (!Closed.contains(successor)) {
                    if (Open.contains(successor)) {
                        UpdateIfShorter( Open,successor, currNode);
                    }
                    else{
                        AddSuccessor(currNode, successor);
                        Open.add(successor);
                    }
                }
            }
            Closed.add(currNode);
        }
        return null;
    }

    protected void UpdateIfShorter(PriorityQueue<AState> Open, AState successor, AState currNode){
        ArrayList<AState> temp = new ArrayList<>();
        while(!Open.isEmpty()) {
            AState old = Open.poll();
            if (old.equals(successor)){
                if ((successor.getCost() + currNode.getCost()) < old.getCost()) {
                    AddSuccessor(currNode, successor);
                    Open.add(successor);
                }
                else
                    Open.add(old);
                break;
            }
            else
                temp.add(old);
        }
        Open.addAll(temp);
    }

    protected void AddSuccessor(AState curr, AState Successor){
        Successor.setCameFrom(curr);
        Successor.setCost(curr.getCost() + 1);
    }

}
