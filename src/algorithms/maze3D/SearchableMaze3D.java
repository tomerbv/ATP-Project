package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    Maze3D maze;

    /** start state Getter
     * @return AState - the state which is the start position of the maze.
     */
    @Override
    public AState getStartState() throws Exception {
        return new Maze3DState(maze.getStartPosition());
    }

    /** goal state Getter
     * @return AState - the state which is the goal position of the maze.
     */
    @Override
    public AState getGoalState() throws Exception {
        return new Maze3DState(maze.getGoalPosition());
    }

     /** Get all Successors (possible Positions to advance to in the maze) from a certain state
     @param  s a certain state the searching algorithm moves from or to.
     @return ArrayList<AState> - all the states possible to advance to from that certain state.
     */

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) throws Exception {
        ArrayList<AState> successors = new ArrayList< >();
        int k = ((Maze3DState) s).getPosition().getDepthIndex();
        int i = ((Maze3DState) s).getPosition().getRowIndex();
        int j = ((Maze3DState) s).getPosition().getColumnIndex();
        boolean up = false;
        boolean down = false;
        boolean right = false;
        boolean left = false;

        //Check up
        if(i > 0 && maze.GetPositionVal(k,i - 1, j) == 0){
            successors.add(new Maze3DState(new Position3D(k,i - 1, j), 10));
            up = true;
        }
        //Check down
        if(i < (maze.getRows() - 1) && maze.GetPositionVal(k,i + 1, j) == 0){
            successors.add(new Maze3DState(new Position3D(k,i + 1, j), 10));
            down = true;
        }
        //Check right
        if(j < (maze.getColumns() - 1) && maze.GetPositionVal(k,i, j + 1) == 0){
            successors.add(new Maze3DState(new Position3D(k,i, j+ 1), 10));
            right = true;
        }
        //Check left
        if(j > 0 && maze.GetPositionVal(k,i, j - 1) == 0){
            successors.add(new Maze3DState(new Position3D(k,i, j - 1), 10));
            left = true;
        }
        //Check diagonals
        //Up Right
        if( (i > 0 && j < (maze.getColumns() - 1)) && (up || right) && maze.GetPositionVal(k,i - 1, j + 1) == 0)
            successors.add(new Maze3DState(new Position3D(k,i - 1, j + 1), 15));
        //Up Left
        if( (i > 0 && j > 0) && (up || left) && maze.GetPositionVal(k,i - 1, j - 1) == 0)
            successors.add(new Maze3DState(new Position3D(k,i - 1, j - 1), 15));
        //Down Left
        if( (i < (maze.getRows() - 1) && j > 0) && (down || left) && maze.GetPositionVal(k,i + 1, j - 1) == 0)
            successors.add(new Maze3DState(new Position3D(k,i + 1, j - 1), 15));
        //Down Right
        if( (i < (maze.getRows() - 1) && j < (maze.getColumns() - 1)) && (down || right) && maze.GetPositionVal(k,i + 1, j + 1) == 0)
            successors.add(new Maze3DState(new Position3D(k,i + 1, j + 1), 15));

        //Check Deep
        if(k > 0 && maze.GetPositionVal(k-1, i , j) == 0){
            successors.add(new Maze3DState(new Position3D(k-1, i , j), 10));
        }
        //Check Shallow
        if(k < (maze.getDepth() - 1) && maze.GetPositionVal(k+1,i, j) == 0){
            successors.add(new Maze3DState((new Position3D(k+1,i, j)), 10));
        }
        return successors;


    }

    public SearchableMaze3D(Maze3D maze){
        this.maze = maze;
    }
}
