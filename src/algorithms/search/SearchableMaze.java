package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    Maze maze;

    @Override
    public AState getStartState(){
        return new MazeState(maze.getStartPosition());

    }
    @Override
    public AState getGoalState(){
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
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
        if( (i > 0 && j < (maze.getColumns() - 1)) && (up || right))
            successors.add(new MazeState(new Position(i - 1, j + 1), 15));
        //Up Left
        if( (i > 0 && j > 0) && (up || left))
            successors.add(new MazeState(new Position(i - 1, j - 1), 15));
        //Down Left
        if( (i < (maze.getRows() - 1) && j > 0) && (down || left))
            successors.add(new MazeState(new Position(i - 1, j - 1), 15));
        //Down Right
        if( (i < (maze.getRows() - 1) && j < (maze.getColumns() - 1)) && (down || right))
            successors.add(new MazeState(new Position(i - 1, j + 1), 15));

        return successors;


    }

    public SearchableMaze(Maze maze){
       this.maze = maze;
    }
}

