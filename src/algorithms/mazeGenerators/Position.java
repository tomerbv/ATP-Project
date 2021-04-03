package algorithms.mazeGenerators;

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
}
