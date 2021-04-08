package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * a class that represents the solution of the maze
 * Goal is the Astate that we reached
 */
public class Solution {
    AState Goal;


    public Solution(AState Goal) {
        this.Goal = Goal;
    }

    /**
     * @return the path from the Astate Goal to the Start of the Searchable problem
     */
    public ArrayList<AState> getSolutionPath(){
        Stack<AState> reversePath = new Stack<AState>();
        reversePath.push(Goal);
        AState Curr = Goal.getCameFrom();
        while(Curr != null){
            reversePath.push(Curr);
            Curr = Curr.getCameFrom();
        }
        ArrayList<AState> Path = new ArrayList<AState>();
        while(!reversePath.isEmpty()){
            Path.add(reversePath.pop());
        }
        return Path;
    }
}


