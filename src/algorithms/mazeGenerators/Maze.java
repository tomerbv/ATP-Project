package algorithms.mazeGenerators;

public class Maze {
    int rows;
    int columns;
    Position start;
    Position goal;
    int[][] maze;

    public Maze(int rows, int columns, Position start ,Position goal) {
        this.start = start;
        this.goal = goal;
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];
    }

    public void set(int row, int column, int val){
        this.maze[row][column] = val;
    }

    public Position getStartPosition(){
        return this.start;
    }

    public Position getGoalPosition(){
        return this.goal;
    }

    public int GetPosition(int i, int j){return this.maze[i][j];}

    public void print(){
        for (int i=0; i <= rows - 1; i++){
            if(i == this.start.getRowIndex() && 0 == this.start.getColumnIndex())
                System.out.print("{ " + "S" + " ");
            else if(i == this.goal.getRowIndex() && 0 == this.goal.getColumnIndex())
                System.out.print("{ " + "E" + " ");
            else
                System.out.print("{ " + this.maze[i][0] + " ");
            for (int j=1; j <= columns - 1; j++){
                if(i == this.start.getRowIndex() && j == this.start.getColumnIndex())
                    System.out.print("S ");
                else if(i == this.goal.getRowIndex() && j == this.goal.getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print( this.maze[i][j] + " ");
            }
            System.out.println("}");
        }
    }

    public void superprint(){
        System.out.print("int[][] maze = {\n");
        for (int i=0; i <= rows - 1; i++){
            if(i == this.start.getRowIndex() && 0 == this.start.getColumnIndex())
                System.out.print("\t\t\t{" + "S ");
            else if(i == this.goal.getRowIndex() && 0 == this.goal.getColumnIndex())
                System.out.print("\t\t\t{" + "E ");
            else
                System.out.print("\t\t\t{" + charprint(this.maze[i][0]));
            for (int j=1; j <= columns - 1; j++){
                if(i == this.start.getRowIndex() && j == this.start.getColumnIndex())
                    System.out.print("S ");
                else if(i == this.goal.getRowIndex() && j == this.goal.getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print(charprint(this.maze[i][j]));
            }
            System.out.println("},");
        }
        System.out.println("\t};");
    }

    private String charprint(int i){
        if(i == 1)
            return ("██");
        else
            return("  ");
    }
}

