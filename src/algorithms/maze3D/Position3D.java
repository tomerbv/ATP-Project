package algorithms.maze3D;

import java.util.Objects;

public class Position3D {
    int depth;
    int row;
    int column;

    /** Constructor
     * @param row the point's row index.
     * @param column the point's column index
     */
    public Position3D(int depth, int row, int column) throws Exception {
        if(depth < 0 || row < 0 || column < 0)
            throw new Exception("Invalid Dimension Size");
        this.depth = depth;
        this.row = row;
        this.column = column;
    }

    /** Depth index getter
     * @return int depth index
     */
    public int getDepthIndex(){return this.depth;}

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
        return "{" + depth + "," + row + ',' + column +"}";
    }

    /** Basic equals method to determine two positions are the same.
     * @param o object to compare equality to
     * @return boolean true if equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position3D)) return false;
        Position3D position3D = (Position3D) o;
        return (depth == position3D.depth && row == position3D.row && column == position3D.column);
    }

    /** Basic hashcode method to distinguish a position
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(depth, row, column);
    }
}
