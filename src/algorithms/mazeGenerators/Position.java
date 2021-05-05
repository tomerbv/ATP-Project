package algorithms.mazeGenerators;

import java.util.Objects;

/** Position class represents a cell in the grid of the maze,
 *  Holds 2 data members int row and int column for a specific cell.
 */
public class Position {
    int row;
    int column;

    /** Constructor
     * @param row the point's row index.
     * @param column the point's column index
     */
    public Position(int row, int column){
        try{
            if(row < 0 || column < 0)
                throw new Exception("Invalid Dimension Size");
            this.row = row;
            this.column = column;
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    /** Row index getter
     * @return int row index
     */
    public int getRowIndex(){return this.row;}

    /** Column index getter
     * @return int column index
     */
    public int getColumnIndex(){return this.column;}

    /** Basic toString method representing a position with 2 coordinates.
     * @return String {row,column}
     */
    @Override
    public String toString() {
        return "{" + row + ',' + column +"}";
    }

    /** Basic equals method to determine two positions are the same.
     * @param o object to compare equality to
     * @return boolean true if equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return (row == position.row && column == position.column);
    }

    /** Basic hashcode method to distinguish a position
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
