package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * a class that represents the solution of the maze.
 * Goal is the Astate that we reached.
 */
public class Solution {
    AState Goal;

    /** Constructor for a non existing Solution. */
    public Solution(){
        this.Goal = null;
    }

    /** Constructor for an existing Solution.
     * @param Goal The Gaol state of the solution with it's backtracking nodes that create a path.
     */
    public Solution(AState Goal) {
        this.Goal = Goal;
    }

    /** getSolutionPath returns an ArrayList of the states involved in the
     * solution or an empty ArrayList if there isn't a valid solution.
     * @return the path from the Astate Goal to the Start of the Searchable problem.
     */
    public ArrayList<AState> getSolutionPath(){
        Stack<AState> reversePath = new Stack<AState>();
        ArrayList<AState> Path = new ArrayList<AState>();
        if(!(this.Goal == null)){
            reversePath.push(Goal);
            AState Curr = Goal.getCameFrom();
            while(Curr != null){
                reversePath.push(Curr);
                Curr = Curr.getCameFrom();
            }

            while(!reversePath.isEmpty()){
                Path.add(reversePath.pop());
            }
        }
        return Path;
    }
}


