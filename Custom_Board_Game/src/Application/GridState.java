package Application;

public enum GridState {
    EMPTY, OCCUPIED1, OCCUPIED2, OCCUPIED3, OCCUPIED4, OCCUPIED5, INVALID;

    public static GridState createState(String s) {
        switch (s) {
            case ("EMPTY"):
                return EMPTY;
            case ("OCCUPIED1"):
                return OCCUPIED1;
            case ("OCCUPIED2"):
                return OCCUPIED2;
            case ("OCCUPIED3"):
                return OCCUPIED3;
            case ("OCCUPIED4"):
                return OCCUPIED4;
            case ("OCCUPIED5"):
                return OCCUPIED5;
        }
        return INVALID;
    }

    public synchronized static GridState gridState(String color) {

        color = color.toUpperCase().trim();
        switch (color) {
            case "RED":
                return GridState.OCCUPIED1;
            case "BLUE":
                return GridState.OCCUPIED2;
            case "GREEN":
                return GridState.OCCUPIED3;
            case "PINK":
                return GridState.OCCUPIED4;
            case "YELLOW":
                return GridState.OCCUPIED5;
            default:
                return GridState.EMPTY;
        }


    }
}
