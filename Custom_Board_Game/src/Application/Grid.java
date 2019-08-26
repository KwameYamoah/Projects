package Application;


import java.awt.*;

public class Grid {
    public Point coordinate;
    public GridState state;

    public Grid(Point point, GridState state) {
        coordinate = point;
        this.state = state;
    }

    boolean changeState(GridState state) {
        if (this.state == GridState.EMPTY) {
            this.state = state;
            return true;
        } else {
            return false;
        }
    }

    boolean isEmpty() {
        return state == GridState.EMPTY;
    }

    @Override
    public String toString() {
        return "Grid(" + coordinate.y + "," + coordinate.x + ")" + state.toString();
    }

    void forceChangeState(GridState gridState) {
        this.state = gridState;
    }
}
