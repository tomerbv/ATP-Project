package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    Node start;
    Node goal;

    public SearchableMaze(Maze maze){
        this.start = new Node(maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex());
        this.goal = new Node(maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex());
        this.start.NeighborsToNodes(maze);
    }
   // public getAllSuccessors



    private class Node extends Position{
        ArrayList<Node> neighbors;

        public Node(int row, int columns){
            super(row,columns);
            this.neighbors = new ArrayList<Node>();
        }

        public void NeighborsToNodes(Maze maze){
            int i = this.getRowIndex();
            int j = this.getColumnIndex();

            if (i == 0) {
                //Upper right corner
                if (j == 0) {
                    neighbors.add(new Node(i + 1, j));
                    neighbors.add(new Node(i, j + 1));
                }
                //Upper left corner
                else if (j == (maze.getColumns() - 1)) {
                    neighbors.add(new Node(i + 1, j));
                    neighbors.add(new Node(i, j - 1);
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
    }
}
