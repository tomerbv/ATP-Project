package algorithms.mazeGenerators;

/**
 * Maze class holds 3 data members: the grid of integers and two Position types - starting and goal points.
 */
public class Maze {
    Position start;
    Position goal;
    int[][] map;

    /** Constructor
     * @param start - Determines the starting position of the maze.
     * @param goal - Determines the goal position of the maze.
     * @param map - a 3 Dimension grid of integers representing the maze.
     */
    public Maze(Position start ,Position goal, int[][] map) throws Exception {
        if(start == null || goal == null || map == null){
            throw new Exception("Null Arguments");
        }
        this.start = start;
        this.goal = goal;
        this.map = map;
    }

    /** Grid setter by index.
     * @param row - The specified cell's row.
     * @param column - The specified cell's column.
     * @param val - The value to set to that cell.
     */
    public void set(int row, int column, int val){
        this.map[row][column] = val;
    }

    /** Start Position getter.
     * @return Position start.
     */
    public Position getStartPosition(){
        return this.start;
    }

    /** Goal Position getter.
     * @return Position goal.
     */
    public Position getGoalPosition(){
        return this.goal;
    }

    /** Row num getter.
     * @return int row.
     */
    public int getRows(){ return this.map.length; }

    /** Column num getter.
     * @return int column.
     */
    public int getColumns(){ return this.map[0].length; }

    /** Grid getter by index.
     * @param i - The specified cell's row.
     * @param j - The specified cell's column.
     * @return int value of that cell.
     */
    public int GetPositionVal(int i, int j){return this.map[i][j];}

    /**
     * Basic printer for a basic representation of the maze represented by 1's and 0's.
     */
    public void print(){
        for (int i=0; i <= getRows() - 1; i++){
            if(i == this.start.getRowIndex() && 0 == this.start.getColumnIndex())
                System.out.print("{ " + "S" + " ");
            else if(i == this.goal.getRowIndex() && 0 == this.goal.getColumnIndex())
                System.out.print("{ " + "E" + " ");
            else
                System.out.print("{ " + this.map[i][0] + " ");
            for (int j=1; j <= getColumns() - 1; j++){
                if(i == this.start.getRowIndex() && j == this.start.getColumnIndex())
                    System.out.print("S ");
                else if(i == this.goal.getRowIndex() && j == this.goal.getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print( this.map[i][j] + " ");
            }
            System.out.println("}");
        }
    }


    /**
     * Customized print for a better visual grasp of the maze.
     */
    public void superprint(){
        for (int i=0; i <= getRows() - 1; i++){
            if(i == this.start.getRowIndex() && 0 == this.start.getColumnIndex())
                System.out.print("{" + " S ");
            else if(i == this.goal.getRowIndex() && 0 == this.goal.getColumnIndex())
                System.out.print("{" + " E " );
            else
                System.out.print("{" + charprint(this.map[i][0]) + " ");
            for (int j=1; j <= getColumns() - 1; j++){
                if(i == this.start.getRowIndex() && j == this.start.getColumnIndex())
                    System.out.print(" S ");
                else if(i == this.goal.getRowIndex() && j == this.goal.getColumnIndex())
                    System.out.print(" E ");
                else
                    System.out.print(charprint(this.map[i][j]) + " ");
            }
            System.out.println("}");
        }
    }

    /** Private utility method for superprint method.
     * @param i 0 or 1 indicating a wall or a path.
     * @return String consisting of 2 chars' representing a wall or a path.
     */
    private String charprint(int i){
        if(i == 1)
            return ("██");
        else
            return("  ");
    }
}

