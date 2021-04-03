package algorithms.mazeGenerators;
import java.lang.Math;

public class SimpleMazeGenerator extends AMazeGenerator  {
    public Maze generate(int rows, int columns){
        Position start = new Position(0,0);
        Position goal = new Position(rows - 1,columns - 1);
        Maze maze = new Maze(rows,columns, start,goal);
        for (int i=0; i <= rows - 1; i++){
            for (int j=0; j <= columns - 1; j++){
                maze.set(i,j,1);
            }
        }
        double dir = 0.2;
        for(int k = 0; k < 3; k++){
            int i = 0;
            int j = 0;
            while(i != rows - 1 || j != columns - 1){
                maze.set(i,j,0);
                if( ((Math.random() > dir) && i < rows - 1) || j == columns - 1)
                    i++;
                else
                    j++;
            }
            dir += 0.3;
        }
        maze.set(rows - 1,columns - 1,0);
        return maze;
    }
}
