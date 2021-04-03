package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator  {
    public Maze generate(int rows, int columns){
        Position start = new Position(0,0);
        Position goal = new Position(rows - 1,columns - 1);
        Maze maze = new Maze(rows,columns, start,goal);
        for (int i=0; i <= rows - 1; i++){
            for (int j=0; j <= columns - 1; j++){
                maze.set(i,j,0);
            }
        }
        return maze;
    }
}
