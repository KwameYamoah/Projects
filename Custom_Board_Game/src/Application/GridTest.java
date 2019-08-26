package Application;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class GridTest {
    @Test
    public void changeState() throws Exception {
        Grid grid = new Grid(new Point(1, 1), GridState.EMPTY);
        grid.changeState(GridState.OCCUPIED1);
        assertEquals(grid.state, GridState.OCCUPIED1);
        assertEquals(grid.changeState(GridState.OCCUPIED3), false);

    }

    @Test
    public void forceChangeState() throws Exception {

        Grid grid = new Grid(new Point(1, 1), GridState.EMPTY);
        grid.changeState(GridState.OCCUPIED1);
        assertEquals(grid.state, GridState.OCCUPIED1);
        grid.forceChangeState(GridState.OCCUPIED3);
        assertEquals(grid.state, GridState.OCCUPIED3);

    }

}