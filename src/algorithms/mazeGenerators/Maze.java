package algorithms.mazeGenerators;

import java.util.ArrayList;

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
    public Maze(byte[] getinfo) throws Exception{
        ArrayList<Integer> dimenions = new ArrayList<Integer>();

        int columns;
        int dim=0;
        int counter = 0;
        while(((int)getinfo[counter])<0){
            dim = dim*255;
            counter++;
        }
        dim = dim + getinfo[counter];
        rows = dim
       this.map = new int[getinfo[0]][getinfo[1]];
       this.start = new Position(getinfo[2],getinfo[3]);
       this.goal = new Position(getinfo[4],getinfo[5]);
       int place = 6;
        for (int i = 0; i < getinfo[0]; i++) {
            for (int j = 0; j < getinfo[1]; j++) {
                this.map[i][j] = getinfo[place];
                place++;
            }
        }



    }

    /** Grid setter by index.
     * @param row - The specified cell's row.
     * @param column - The specified cell's column.
     * @param val - The value to set to that cell.
     */
    public int CheckDimesons
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
    public byte[] toByteArray() {
        byte[] bytearray = new byte[(this.getRows() * this.getColumns()) + 6];
        bytearray[0] = (byte) this.getRows();
        bytearray[1] = (byte) this.getColumns();
        bytearray[2] = (byte) this.getStartPosition().row;
        bytearray[3] = (byte) this.getStartPosition().column;
        bytearray[4] = (byte) this.getGoalPosition().row;
        bytearray[5] = (byte) this.getGoalPosition().column;
        int place = 6;
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                bytearray[place] = (byte) GetPositionVal(i, j);
                place++;

            }

        }
        return bytearray;
    }



}

