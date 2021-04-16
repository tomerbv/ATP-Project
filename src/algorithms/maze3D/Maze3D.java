package algorithms.maze3D;

public class Maze3D {
    Position3D startPosition;
    Position3D goalPosition;
    int[][][] map;

    /**Constructor
     * @param start - Determines the starting position of the maze.
     * @param goal - Determines the goal position of the maze.
     * @param map3D - a 3 Dimension grid of integers representing the maze
     */
    public Maze3D( Position3D start ,Position3D goal, int[][][] map3D) throws Exception {
        if(start == null || goal == null || map3D == null){
            throw new Exception("Null Arguments");
        }
        this.startPosition = start;
        this.goalPosition = goal;
        this.map = map3D;
    }

    /** Grid setter by index.
     * @param depth The specified cell's depth.
     * @param row - The specified cell's row.
     * @param column - The specified cell's column.
     * @param val - The value to set to that cell.
     */
    public void set(int depth, int row, int column, int val){this.map[depth][row][column] = val; }

    /** map getter
     * @return returns the map
     */
    public int[][][] getMap(){return map;}

    /** Start Position getter.
     * @return Position start.
     */
    public Position3D getStartPosition(){return this.startPosition; }

    /** Goal Position getter.
     * @return Position goal.
     */
    public Position3D getGoalPosition(){
        return this.goalPosition;
    }

    /** Depth dimension getter.
     * @return int depth.
     */
    public int getDepth(){ return this.map.length; }

    /** Row dimension getter.
     * @return int row.
     */
    public int getRows(){ return this.map[0].length; }

    /** Column dimension getter.
     * @return int column.
     */
    public int getColumns(){ return this.map[0][0].length; }

    /** Grid getter by index.
     * @param depth - The specified cell's depth.
     * @param row - The specified cell's row.
     * @param column - The specified cell's column.
     * @return int value of that cell.
     */
    public int GetPositionVal(int depth, int row, int column){return this.map[depth][row][column];}

    /**
     * Basic printer for a basic representation of the maze represented by 1's and 0's.
     */
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < map.length; depth++){
            for(int row = 0; row < map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < map[0][0].length; col++) {
                    if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(map[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    /**
     * Customized print for a better visual grasp of the maze.
     */
    public void superprint(){
        System.out.println("{");
        for(int depth = 0; depth < map.length; depth++){
            for(int row = 0; row < map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < map[0][0].length; col++) {
                    if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print(" S ");
                    else {
                        if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print(" E ");
                        else
                            System.out.print(charprint(map[depth][row][col]) + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    /** Private utility method for superprint method.
     * @param i 0 or 1 indicating a wall or a path.
     * @return String consisting of 2 chars representing a wall or a path.
     */
    private String charprint(int i){
        if(i == 1)
            return ("██");
        else
            return("  ");
    }
}
