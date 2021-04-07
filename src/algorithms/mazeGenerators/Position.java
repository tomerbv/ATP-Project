package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {
    int row;
    int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRowIndex(){return this.row;}
    public int getColumnIndex(){return this.column;}

    @Override
    public String toString() {
        return "{" + row + ',' + column +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return (row == position.row && column == position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
