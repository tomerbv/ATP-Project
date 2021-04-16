package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * represents the DFS algorithm
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    /**
     * @param domain the domain we are using the algorithm on.
     * open - a stack data structure that holds the states evalueted from the first one(at the bottom of the stack) to the top
     *        (the goal).
     * close - a set of all the states we have reached in the algorithm, used for not double checking states we
     *         have already reached.
     * successors - an arraylist that holds the successors of each state for evaluation and insertion into open
     * the algoritihm starts at the goal start state and pushes it into the stack,then pop is it and for every successor it has it
     * checks if it is in open and closed(so we dont make the same step twice). then the current state we are in is pushed and then
     * the succesor, then the successor becomes the current state and the cycle continues until we reach the goal state
     * @return an object of type solution with the goal state
     */
    @Override
    public Solution solve(ISearchable domain) throws Exception {
        if (domain == null)
            return null;
        this.NodesEvaluated = 0;
        Stack<AState> Open = new Stack<AState>();
        HashSet<AState> Closed = new HashSet<AState>();
        ArrayList<AState> Successors;
        Open.push(domain.getStartState());
        AState currNode;
        while (!Open.isEmpty()) {
            currNode = Open.pop();
            Successors = domain.getAllSuccessors(currNode);
            for (int i = 0; i < Successors.size(); i++){
                if (currNode.equals(domain.getGoalState())) {
                    this.NodesEvaluated++;
                    return new Solution(currNode);
                }
                AState successor = Successors.get(i);
                if (!Closed.contains(successor)) {
                    if (!Open.contains(successor)) {
                        this.NodesEvaluated ++;
                        successor.setCameFrom(currNode);
                        successor.setCost(currNode.getCost() + 1);
                        Open.push(currNode);
                        Open.push(successor);
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
