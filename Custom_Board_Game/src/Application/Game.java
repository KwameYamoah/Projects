package Application;

import Application.Socket.Move;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Application.Constants.*;


public class Game {
    Board board;
    public static List<Grid> grids = new ArrayList<>();
    private Random random = new Random();

    public Game() {
        createEmptyGrids();
        board = new Board(grids);
    }


    private void createEmptyGrids() {
        for (int y = START_GRID_POINT; y < (ROW * SIZE) + START_GRID_POINT; y += SIZE) {
            for (int x = START_GRID_POINT; x < (COLUMN * SIZE) + START_GRID_POINT; x += SIZE) {
                grids.add(new Grid(new Point(x, y), GridState.EMPTY));
            }
        }
    }

    public void placeStartStone(GridState gridState) {
        boolean foundEmptyGrid = false;
        while (!foundEmptyGrid) {
            int ranGridNumber = random.nextInt(60);
            if (grids.get(ranGridNumber).changeState(gridState)) {
                foundEmptyGrid = true;
            }
        }

    }

    public boolean changeState(Move move, GridState player) {
        switch (move.cardType) {
            case NO_CARD:
                return Board.placeStone(move.cell[0].column, move.cell[0].row, player);
            case FREEDOM_CARD:
                return Board.freedomCard(move.cell[0].column, move.cell[0].row, player);
            case REPLACEMENT_CARD:
                return Board.replacementCard(move.cell[0].column, move.cell[0].row, player);
            case DOUBLE_CARD:
                return Board.doubleCard(move.cell[0].column, move.cell[0].row, move.cell[1].column, move.cell[1].row, player);
        }
        return false;
    }

    public boolean notGameOver() {
        for (Grid grid : grids) {
            if (grid.state == GridState.EMPTY) {
                return true;
            }
        }
        return false;
    }

    public static List<Grid> getGrids() {
        return grids;
    }
}
