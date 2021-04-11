package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    /**
     * @param depth   - Determines dimension of depth for the created maze
     * @param rows    - Determines dimension of rows for the created maze
     * @param columns - Determines dimension of columns for the created maze
     * @return Maze - a generated maze with walls spread randomly using Randomized Prim algorithm.
     */
    public Maze3D generate(int depth, int rows, int columns) {
        if(depth < 2 || rows < 2 || columns < 2 )
            return null;
        Position3D start = RandomWall3D(depth, rows, columns);
        Position3D goal = new Position3D((depth - 1) - start.getDepthIndex(),(rows - 1) - start.getRowIndex(), (columns - 1) - start.getColumnIndex());
        HashSet<Position3D> Unvisited = new HashSet<Position3D>(); //
        int[][][] map3D = new int[depth][rows][columns];
        Maze3D maze = new Maze3D(start, goal, map3D);
        for (int k = 0; k <= depth - 1; k++) {
            for (int i = 0; i <= rows - 1; i++) {
                for (int j = 0; j <= columns - 1; j++) {
                    maze.set(k,i, j, 1);
                    Unvisited.add(new Position3D(k,i, j));
                }
            }
        }
        int randindex, connect = 0;
        maze.set(start.getDepthIndex(), start.getRowIndex(), start.getColumnIndex(), 0);
        Unvisited.remove(start);
        ArrayList<Position3D> neighbors = GetNeighbors(maze, depth, rows, columns, start.getDepthIndex(), start.getRowIndex(), start.getColumnIndex());
        while (!neighbors.isEmpty()) {
            randindex = (int) (Math.random() * (neighbors.size() - 1));
            Position3D neighbor = neighbors.get(randindex);
            Unvisited.remove(neighbor);
            /** first we check if a the cell is the goal so the path will exist without doubt */
            if(neighbor.getRowIndex() == goal.getRowIndex() && neighbor.getColumnIndex() == goal.getColumnIndex()) {
                maze.set(neighbor.getDepthIndex(), neighbor.getRowIndex(), neighbor.getColumnIndex(), 0);
            }
            /** then if that neighbor can be a path we set him to the value 0 and add his neighbors to the arraylist */
            else if(maze.GetPositionVal(neighbor.getDepthIndex(), neighbor.getRowIndex(),neighbor.getColumnIndex()) != 0 && CanBePath(neighbor, maze, depth, rows, columns, connect)){
                maze.set(neighbor.getDepthIndex(), neighbor.getRowIndex(),neighbor.getColumnIndex(),0);
                neighbors.addAll(GetNeighbors(maze, depth, rows, columns, neighbor.getDepthIndex(), neighbor.getRowIndex(), neighbor.getColumnIndex()));
            }
            /** anyway, we remove the cell from the arraylist BY INDEX to improve time complexity */
            neighbors.remove(randindex);

            if(neighbors.isEmpty() && !(Unvisited.isEmpty())){
                neighbor = Unvisited.iterator().next();
                Unvisited.remove(neighbor);
                neighbors.add(neighbor);
                /** if the goal position hasent been reached we make sure the maze is solvable
                 * by tearing walls that have more than one path neghbor.*/
                if(maze.GetPositionVal(neighbor.getDepthIndex(), goal.getRowIndex(),goal.getColumnIndex()) != 0)
                    connect = 1;
            }
        }
        return maze;
    }

    /** Utility method to get a Position on one of the mazes walls randomly.
     * @param depth - The mazes depth dimension
     * @param rows - The mazes rows dimension
     * @param columns - The mazes columns dimension
     * @return Position - a random Position on one of the mazes walls
     */
    private Position3D RandomWall3D(int depth, int rows, int columns){
        int k,i,j;
        double rand = Math.random();
        /** depth axis */
        if(rand > 1/3){
            i = (int) (Math.random() * (rows - 1));
            j = (int) (Math.random() * (columns - 1));
            if(Math.random() > 0.5)
                k = 0;
            else
                k = depth - 1;
        }
        /** row axis */
        else if(rand > 2/3){
            k = (int) (Math.random() * (depth - 1));
            j = (int) (Math.random() * (columns - 1));
            if(Math.random() > 0.5)
                i = 0;
            else
                i = rows - 1;
        }
        /** column axis */
        else{
            k = (int) (Math.random() * (depth - 1));
            i = (int) (Math.random() * (rows - 1));
            if(Math.random() > 0.5)
                j = 0;
            else
                j = columns - 1;
        }
        return new Position3D(k,i,j);

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
    private boolean CanBePath(Position3D pos, Maze3D maze ,int depth, int rows, int columns, int connect){
        ArrayList<Position3D> neighbors = GetNeighbors( maze , depth, rows, columns, pos.getDepthIndex(), pos.getRowIndex() ,pos.getColumnIndex());
        int counter = 0;
        for (Position3D neighbor: neighbors) {
            if (maze.GetPositionVal(neighbor.getDepthIndex(), neighbor.getRowIndex(),neighbor.getColumnIndex()) == 0)
                counter ++;
            if (counter > 1 + connect)
                return false;
        }
        return true;
    }

    /** Utility method to get the neighbors of a certain cell' while making sure they are within the boundaries of the maze
     * @param maze The current maze
     * @param rows Mazes rows dimension
     * @param columns Mazes rows dimension
     * @param i The questioned Position's row index
     * @param j The questioned Position's column index
     * @return ArrayList of Positions that are neighbors of the Position in question.
     */
    private ArrayList<Position3D> GetNeighbors(Maze3D maze , int depth, int rows, int columns, int k, int i, int j) {
        ArrayList<Position3D> neighbors = new ArrayList<Position3D>();
        /** Depth dimension neighbors. */
        if(k == 0)
            neighbors.add(new Position3D(k+1,i, j));
        else if(k == depth - 1)
            neighbors.add(new Position3D(k-1,i, j));
        else {
            neighbors.add(new Position3D(k + 1, i, j));
            neighbors.add(new Position3D(k - 1, i, j));
        }
        /** Rows dimension neighbors. */
        if(i == 0)
            neighbors.add(new Position3D(k,i+1, j));
        else if(i == rows - 1)
            neighbors.add(new Position3D(k,i-1, j));
        else {
            neighbors.add(new Position3D(k , i+1, j));
            neighbors.add(new Position3D(k, i-1, j));
        }
        /** Columns dimension neighbors. */
        if(j == 0)
            neighbors.add(new Position3D(k,i, j+1));
        else if(j == columns - 1)
            neighbors.add(new Position3D(k,i, j-1));
        else {
            neighbors.add(new Position3D(k , i, j+1));
            neighbors.add(new Position3D(k, i, j-1));
        }

        return neighbors;
    }
}
