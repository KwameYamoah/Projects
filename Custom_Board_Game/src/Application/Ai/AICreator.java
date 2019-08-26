package Application.Ai;

import Application.Grid;
import Application.GridState;

import static Application.Constants.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AICreator {
    public static void main(String[] args) {
        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            new Thread(new AiRunnable()).start();

    }
}


class AiRunnable implements Runnable {
    @Override
    public void run() {
        AI ai = new AI();
        ai.read();
        while (true) {
            String line = ai.read();
            if (line.contains("Game starting shortly")) {
                String colour = "";
                Grid move = new Grid(new Point(0, 0), GridState.EMPTY);
                while (true) {
                    String response = ai.read();
                    if (response.contains("tag")) {
                        colour = (response.split(" "))[3];
                    } else if (response.contains("12345")) {

                        String[] gridString = response.split("\\r?\\n");
                        String[] grid = Arrays.copyOfRange(gridString, 1, gridString.length);
                        String[] g = {grid[0].substring(1, 11), grid[1].substring(1, 11), grid[2].substring(1, 11), grid[3].substring(1, 11), grid[4].substring(1, 11), grid[5].substring(1, 11)};
                        List<Grid> gridBoard = createBoard(g);
                        move = randomMove(gridBoard, GridState.gridState(colour));
                    } else if (response.contains("It is your turn")) {
                        if (move == null) {
                            break;
                        }
                        int row = (move.coordinate.x - START_GRID_POINT) / SIZE;
                        int column = (move.coordinate.y - START_GRID_POINT) / SIZE;
                        ai.sendCommand("n " + (row + 1) + "." + (column + 1));
                    }
                }
                break;
            }
        }
    }

    private List<Grid> createBoard(String[] strings) {
        List<Grid> gridList = new ArrayList<>();
        int row = 1;
        for (String s : strings) {
            int column = 1;
            while (column <= s.length()) {
                Point point1 = new Point(START_GRID_POINT + ((column - 1) * SIZE), START_GRID_POINT + ((row - 1) * SIZE));
                switch (s.charAt(column - 1)) {
                    case 32:
                        gridList.add(new Grid(point1, GridState.EMPTY));
                        break;
                    case 82:
                        gridList.add(new Grid(point1, GridState.OCCUPIED1));
                        break;
                    case 66:
                        gridList.add(new Grid(point1, GridState.OCCUPIED2));
                        break;
                    case 71:
                        gridList.add(new Grid(point1, GridState.OCCUPIED3));
                        break;
                    case 80:
                        gridList.add(new Grid(point1, GridState.OCCUPIED4));
                        break;
                    case 89:
                        gridList.add(new Grid(point1, GridState.OCCUPIED5));
                        break;
                }

                column++;
            }
            row++;
        }
        return gridList;
    }

    private Grid randomMove(List<Grid> grids, GridState gridState) {
        Random random = new Random();
        List<Grid> move = new ArrayList<>();
        for (Grid grid : grids) {
            if (grid.state == gridState) {
                List<Grid> adjacentGrids = findAdjacentGrids(grid, grids);
                for (Grid g : adjacentGrids) {
                    if (g.state == GridState.EMPTY) {
                        move.add(g);
                    }
                }
            }
        }
        if (!move.isEmpty()) return move.get(random.nextInt(move.size()));
        return null;
    }

    private List<Grid> findAdjacentGrids(Grid g1, List<Grid> grids) {
        List<Grid> adjacentGrids = new ArrayList<>();
        for (Grid g2 : grids) {
            if (isAdjacent(g1, g2)) {
                adjacentGrids.add(g2);
            }
        }
        return adjacentGrids;
    }

    private boolean isAdjacent(Grid g1, Grid g2) {
        if (g1.coordinate.x == g2.coordinate.x && g1.coordinate.y == g2.coordinate.y) {
            return false;
        }
        int diffX = Math.abs((g1.coordinate.x - g2.coordinate.x) / SIZE);
        int diffY = Math.abs((g1.coordinate.y - g2.coordinate.y) / SIZE);
        return (diffX <= 1 && diffY <= 1);
    }


}






