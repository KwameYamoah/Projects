package Application.Socket;

import java.util.Arrays;

public class Move {
    public Cell[] cell;
    public MoveType cardType;

    private Move(MoveType cardType, Cell... cells) {
        this.cardType = cardType;
        this.cell = cells;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static Move parse(String line) {
        try {
            String[] items = line.trim().split("\\s+");
            switch (items[0].toUpperCase()) {
                case "EXIT":
                    return new Move(MoveType.EXIT, (Cell) null);
                case "N":
                    items[1].matches("^[0-9]{1,2}[.][0-9]{1,2}");
                    return new Move(MoveType.NO_CARD, Cell.parse(items[1]));
                case "D":
                    items[1].matches("^[0-9]{1,2}[.][0-9]{1,2}");
                    items[2].matches("^[0-9]{1,2}[.][0-9]{1,2}");
                    return new Move(MoveType.DOUBLE_CARD, Cell.parse(items[1]), Cell.parse(items[2]));
                case "F":
                    items[1].matches("^[0-9]{1,2}[.][0-9]{1,2}");
                    return new Move(MoveType.FREEDOM_CARD, Cell.parse(items[1]));
                case "R":
                    items[1].matches("^[0-9]{1,2}[.][0-9]{1,2}");
                    return new Move(MoveType.REPLACEMENT_CARD, Cell.parse(items[1]));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error trying to parse move");
        } catch (NumberFormatException e) {
            return new Move(MoveType.INVALID, (Cell) null);
        }

        return new Move(MoveType.INVALID, (Cell) null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cardType);
        for (Cell aCell : cell) sb.append(" ").append(aCell);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;
        return Arrays.equals(cell, other.cell) && cardType == other.cardType;
    }
}
