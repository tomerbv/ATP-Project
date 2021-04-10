package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * MyMazeGenerator class generates a maze using Randomized Prim algorithm.
 */
public class MyMazeGenerator extends AMazeGenerator{

    /**
     * @param rows    - Determines number of rows for the created maze
     * @param columns - Determines number of columns for the created maze
     * @return Maze - a generated maze with walls spread randomly using Randomized Prim algorithm.
     */
    public Maze generate(int rows, int columns) {
        Position start = RandomWall(rows, columns);
        Position goal = new Position((rows - 1) - start.getRowIndex(), (columns - 1) - start.getColumnIndex());
        HashSet<Position> Unvisitied = new HashSet<Position>(); //
        Maze maze = new Maze(rows,columns, start,goal);
        /** implemented again in this class because of the addition of Unvisited. */
        for (int i=0; i <= rows - 1; i++){
            for (int j=0; j <= columns - 1; j++){
                maze.set(i,j,1);
                Unvisitied.add(new Position(i,j));
            }
        }
        int connect = 0;
        maze.set(start.getRowIndex(), start.getColumnIndex(), 0);
        Unvisitied.remove(start);
        ArrayList<Position> neighbors = GetNeighbors(maze, rows, columns, start.getRowIndex(), start.getColumnIndex());
        while (!neighbors.isEmpty()) {
            Position neighbor = GetRanNeighbor(neighbors);// choosing a random neighbor

            /** first we check if a the cell is the goal so the path will exist without doubt */
            if(neighbor.getRowIndex() == goal.getRowIndex() && neighbor.getColumnIndex() == goal.getColumnIndex()) {
                maze.set(neighbor.getRowIndex(), neighbor.getColumnIndex(), 0);
            }
             /** then if that neighbor can be a path we set him to the value 0 and add his neighbors to the arraylist */
            else if(maze.GetPositionVal(neighbor.getRowIndex(),neighbor.getColumnIndex()) != 0 && CanBePath(neighbor, maze, rows, columns, connect)){
                maze.set(neighbor.getRowIndex(),neighbor.getColumnIndex(),0);
                neighbors.addAll(GetNeighbors(maze, rows, columns, neighbor.getRowIndex(), neighbor.getColumnIndex()));
            }
             /** anyway, we remove the cell from the arraylist */
            neighbors.remove(neighbor);

            if(neighbors.isEmpty() && !(Unvisitied.isEmpty())){
                neighbor = Unvisitied.iterator().next();
                Unvisitied.remove(neighbor);
                neighbors.add(neighbor);
                /** if the gaol position hasent been reached we make sure the maze is solvable
                 * by tearing walls that have more than one path neghbor.*/
                 if(maze.GetPositionVal(goal.getRowIndex(),goal.getColumnIndex()) != 0)
                    connect = 1;
            }
        }
        return maze;
    }

    /** Utility method to get a Position on one of the mazes walls randomly.
     * @param rows - The mazes rows dimension
     * @param columns - The mazes columns dimension
     * @return Position - a random Position on one of the mazes walls
     */
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

    /** Utility method that checks how many of a cells neighbors are paths to tell if that cell can be a path itself.
     * @param pos The Position that is qualified to be a path
     * @param maze The current maze
     * @param rows Mazes rows dimension
     * @param columns Mazes rows dimension
     * @param connect Either 1 or 0, if the goal position hasn't been reached during the algorithm and a wall barrier needs to be
     *                teared down between the start and goal Positions.
     * @return boolean True if a path can be created or False otherwise.
     */
    private boolean CanBePath(Position pos, Maze maze , int rows, int columns, int connect){
        // checks whether a certain wall can be a path by checking how many of its neighbors are paths themselves.
        ArrayList<Position> neighbors = GetNeighbors( maze ,  rows,  columns, pos.getRowIndex() ,pos.getColumnIndex());
        int counter = 0;
        for (Position neighbor: neighbors) {
            if (maze.GetPositionVal(neighbor.getRowIndex(),neighbor.getColumnIndex()) == 0)
                counter ++;
            if (counter > 1 + connect)
                return false;
        }
        return true;
    }

    /** Utility method to choose a random neighbor from the neighbors ArrayList.
     * @param neighbors Neighbors ArrayList.
     * @return A random Position
     */
    private Position GetRanNeighbor(ArrayList<Position> neighbors) {
        return neighbors.get((int) (Math.random() * (neighbors.size() - 1))); // choosing a random neighbor
    }

    /** Utility method to get the neighbors of a certain cell' while making sure they are within the boundaries of the maze
     * @param maze The current maze
     * @param rows Mazes rows dimension
     * @param columns Mazes rows dimension
     * @param i The questioned Position's row index
     * @param j The questioned Position's column index
     * @return ArrayList of Positions that are neighbors of the Position in question.
     */
    private ArrayList<Position> GetNeighbors(Maze maze , int rows, int columns, int i, int j) {
        //checks where the position is located in relation to the grid and adds returns its neighbors accordingly.
        ArrayList<Position> neighbors = new ArrayList<Position>();
        if (i == 0) {
            //Upper left corner
            if (j == 0) {
                neighbors.add(new Position(i + 1, j));
                neighbors.add(new Position(i, j + 1));
            }
            //Upper right corner
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
