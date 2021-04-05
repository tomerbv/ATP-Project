package algorithms.mazeGenerators;
import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator{

    public Maze generate(int rows, int columns) {
        //First the algorithm initiates the starting and goal positions to be at opposite sides to increase complexity
        //and sets the entire grid to the value 1. Then using Randomized Prim's algorithm we set the start
        //position to the value 0 and add its neighbors to an array. we choose a random neighbor and check whether it's suitable
        //to be a path, if so we set its value to 0 and add its neighbors to the array, otherwise we remove it from
        //the array.

        Position start = RandomWall(rows, columns);
        Position goal = new Position((rows - 1) - start.getRowIndex(), (columns - 1) - start.getColumnIndex());
        Maze maze = new Maze(rows, columns, start, goal);
        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= columns - 1; j++) {
                maze.set(i, j, 1);
            }
        }
        maze.set(start.getRowIndex(), start.getColumnIndex(), 0);
        ArrayList<Position> neighbors = GetNeighbors(maze, rows, columns, start.getRowIndex(), start.getColumnIndex());
        while (!neighbors.isEmpty()) {
            Position neighbor = GetRanNeighbor(neighbors);// choosing a random neighbor

            //first we check if a the cell is the goal so the path will exist without doubt
            if(neighbor.getRowIndex() == goal.getRowIndex() && neighbor.getColumnIndex() == goal.getColumnIndex()) {
                maze.set(neighbor.getRowIndex(), neighbor.getColumnIndex(), 0);
            }
            // then if that neighbor can be a path we set him to the value 0 and add his neighbors to the arraylist
            else if(maze.GetPosition(neighbor.getRowIndex(),neighbor.getColumnIndex()) != 0 && CanBePath(neighbor, maze, rows, columns)){
                maze.set(neighbor.getRowIndex(),neighbor.getColumnIndex(),0);
                neighbors.addAll(GetNeighbors(maze, rows, columns, neighbor.getRowIndex(), neighbor.getColumnIndex()));
            }
            // anyway, we remove the cell from the arraylist
            neighbors.remove(neighbor);

        }
        return maze;
    }

    private Position RandomWall(int rows, int columns){
        // random choice of walls to create the starting and goal positions
        // by randomly fixing i or j to the borders and choosing the other randomly
        int i,j;
        if(Math.random() > 0.5){
            i = (int) (Math.random() * (rows - 1));
            if(Math.random() > 0.5)
                j = 0;
            else
                j = columns - 1;
        }
        else{
            j = (int) (Math.random() * (rows - 1));
            if(Math.random() > 0.5)
                i = 0;
            else
                i = rows - 1;
        }
        return new Position(i,j);

    }
    private boolean CanBePath(Position pos, Maze maze , int rows, int columns){
        // checks whether a certain wall can be a path by checking how many of its neighbors are paths themselves.
        ArrayList<Position> neighbors = GetNeighbors( maze ,  rows,  columns, pos.getRowIndex() ,pos.getColumnIndex());
        int counter = 0;
        for (Position neighbor: neighbors) {
            if (maze.GetPosition(neighbor.getRowIndex(),neighbor.getColumnIndex()) == 0)
                counter ++;
        }
        if (counter > 1)
            return false;
        return true;
    }

    private Position GetRanNeighbor(ArrayList<Position> neighbors) {
        return neighbors.get((int) (Math.random() * (neighbors.size() - 1))); // choosing a random neighbor
    }

    private ArrayList<Position> GetNeighbors(Maze maze , int rows, int columns, int i, int j) {
        //checks where the position is located in relation to the grid and adds returns its neighbors accordingly.
        ArrayList<Position> neighbors = new ArrayList<Position>();
        if (i == 0) {
            //Upper right corner
            if (j == 0) {
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j + 1));
            }
            //Upper left corner
            else if (j == columns - 1) {
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j - 1));
            }
            //Upper wall
            else {
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j - 1));
                neighbors.add(new Position(i, j + 1));
            }
        }
        else if (i == (rows - 1)) {
            //Bottom left corner
            if (j == 0) {
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i, j + 1));
            }
            //Bottom right corner
            else if (j == columns - 1) {
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i, j - 1));
            } else {
                //Bottom Wall
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i, j - 1));
                neighbors.add(new Position(i, j + 1));
            }
        }
        else {
            //Left Wall
            if (j == 0) {
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j + 1));
            }
            //Right Wall
            else if (j == columns - 1) {
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j - 1));
            }
            //No walls around
            else {
                neighbors.add(new Position(i - 1, j));
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j + 1));
                neighbors.add(new Position(i, j - 1));
            }
        }
        return neighbors;
    }
}
