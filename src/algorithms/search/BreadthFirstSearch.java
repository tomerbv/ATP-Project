package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * represents the breathfirstsearch algorithm
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    /**
     * @param domain the domain we need to solve
     * the method recieves a domain and starts at the domains starting state, using the algorithem it reaches the goal state
     * Open - priority queue that has all the states in a certain stage of the algorithm,every
     *        time a state is evaluated it is removed and inserted in to close and inserts in to open
     *        all the states that can be reached from the prior state
     *
     * close - a set of all the states we have reached in the algorithm, used for not double checking states we
     *         have already reached.
     * successors - an arraylist that holds the successors of each state for evaluation and insertion into open
     *
     * @return an object of type solution with the goal state
     */
    @Override
    public Solution solve(ISearchable domain) throws Exception {
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

    /** this method is the only difference between the breathfirst and bestfirst algortihm, while each one changes the cost
     * of the state that will be inserted to the priority queue depending on the specific algorithms desire.
     *
     * @param curr current state
     * @param Successor succesor that will enter the priority queue open
     */

    protected void AddSuccessor(AState curr, AState Successor){
        Successor.setCameFrom(curr);
        Successor.setCost(curr.getCost() + 1);
    }

}
