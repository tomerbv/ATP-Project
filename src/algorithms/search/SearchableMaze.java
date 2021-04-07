package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    Maze maze;

    @Override
    public AState getStartState(){
        //@return AState - the state which is the start position of the maze.
        return new MazeState(maze.getStartPosition());

    }
    @Override
    public AState getGoalState(){
        //@return AState - the state which is the goal position of the maze.
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        /* Get all Successors (possible Positions to advance to in the maze) from a certain state
        @param AState - a certain state the searching algorithm moves from or to.
        @return ArrayList<AState> - all the states possible to advance to from that certain state.
        */
        ArrayList<AState> successors = new ArrayList<AState>();
        int i = ((MazeState) s).position.getRowIndex();
        int j = ((MazeState) s).position.getColumnIndex();
        boolean up = false;
        boolean down = false;
        boolean right = false;
        boolean left = false;

        //Check up
        if(i > 0 && maze.GetPosition(i - 1, j) == 0){
            successors.add(new MazeState(new Position(i - 1, j), 10));
            up = true;
        }
        //Check down
        if(i < (maze.getRows() - 1) && maze.GetPosition(i + 1, j) == 0){
            successors.add(new MazeState(new Position(i + 1, j), 10));
            down = true;
        }
        //Check right
        if(j < (maze.getColumns() - 1) && maze.GetPosition(i, j + 1) == 0){
            successors.add(new MazeState(new Position(i, j+ 1), 10));
            right = true;
        }
        //Check left
        if(j > 0 && maze.GetPosition(i, j - 1) == 0){
            successors.add(new MazeState(new Position(i, j - 1), 10));
            left = true;
        }
        //Check diagonals
        //Up Right
        if( (i > 0 && j < (maze.getColumns() - 1)) && (up || right) && maze.GetPosition(i - 1, j + 1) == 0)
            successors.add(new MazeState(new Position(i - 1, j + 1), 15));
        //Up Left
        if( (i > 0 && j > 0) && (up || left) && maze.GetPosition(i - 1, j - 1) == 0)
            successors.add(new MazeState(new Position(i - 1, j - 1), 15));
        //Down Left
        if( (i < (maze.getRows() - 1) && j > 0) && (down || left) && maze.GetPosition(i + 1, j - 1) == 0)
            successors.add(new MazeState(new Position(i + 1, j - 1), 15));
        //Down Right
        if( (i < (maze.getRows() - 1) && j < (maze.getColumns() - 1)) && (down || right) && maze.GetPosition(i + 1, j + 1) == 0)
            successors.add(new MazeState(new Position(i + 1, j + 1), 15));

        return successors;


    }

    public SearchableMaze(Maze maze){
       this.maze = maze;
    }
}

