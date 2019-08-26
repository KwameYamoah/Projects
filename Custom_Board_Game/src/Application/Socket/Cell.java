package Application.Socket;

import java.util.Arrays;

public class Cell {
    public int row;
    public int column;

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //Creates cell from string 5.4 - >  new Cell(5,4)
    static Cell parse(String line) {
        String[] position = line.split("\\.");
        int row = Integer.parseInt(position[0]);
        int column = Integer.parseInt(position[1]);
        return new Cell(row, column);
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ") ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return row == other.row && column == other.column;
    }
}
