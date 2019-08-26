package Application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Application.Constants.*;


public class Board {
    private List<Grid> grids;
    private static final int fontSize = 30;

    Board(List<Grid> grids) {
        this.grids = grids;
    }

    static boolean placeStone(int column, int row, GridState state) {
        boolean adjacent = false;
        Point point = new Point(START_GRID_POINT + ((row - 1) * SIZE), START_GRID_POINT + ((column - 1) * SIZE));
        Grid g = new Grid(point, GridState.EMPTY);
        if (isRealGridEmpty(g)) {
            adjacent = adjacentMoveAllowed(g, state);
        }
        return adjacent && findAndChangeGridState(point, state, false);
    }

    static boolean doubleCard(int column1, int row1, int column2, int row2, GridState state) {
        Point point1 = new Point(START_GRID_POINT + ((row1 - 1) * SIZE), START_GRID_POINT + ((column1 - 1) * SIZE));
        Point point2 = new Point(START_GRID_POINT + ((row2 - 1) * SIZE), START_GRID_POINT + ((column2 - 1) * SIZE));

        boolean firstAdjacent = false;
        boolean secondAdjacent = false;
        Grid g = new Grid(point1, GridState.EMPTY);
        if (isRealGridEmpty(g)) {
            System.out.println("First grid is empty");
            firstAdjacent = adjacentMoveAllowed(g, state);
        }
        if (firstAdjacent) {
            System.out.println("Valid first card");
            if (findAndChangeGridState(point1, state, false)) {
                System.out.println("State of first grid changed");
                Grid g2 = new Grid(point2, GridState.EMPTY);
                System.out.println(g2);
                if (isRealGridEmpty(g2)) {
                    System.out.println("second grid empty");
                    secondAdjacent = adjacentMoveAllowed(g2, state);
                }
                if (secondAdjacent) {
                    System.out.println("Valid second card");
                    return findAndChangeGridState(point2, state, false);
                }
                findAndChangeGridState(point1, GridState.EMPTY, true);
            }
        }
        return false;
    }

    static boolean replacementCard(int column, int row, GridState state) {
        boolean adjacent = false;
        Point point = new Point(START_GRID_POINT + ((row - 1) * SIZE), START_GRID_POINT + ((column - 1) * SIZE));
        Grid g = new Grid(point, GridState.EMPTY);

        if (!isRealGridEmpty(g)) {
            System.out.println("Grid is occupied - checking if move is adjacent");
            adjacent = adjacentMoveAllowed(g, state);
        }
        return adjacent && findAndChangeGridState(point, state, true);
    }

    static boolean freedomCard(int column, int row, GridState state) {
        Point point = new Point(START_GRID_POINT + ((row - 1) * SIZE), START_GRID_POINT + ((column - 1) * SIZE));
        return findAndChangeGridState(point, state, false);
    }


    private static boolean isRealGridEmpty(Grid g) {
        for (Grid grids : Game.getGrids()) {
            if (grids.coordinate.x == g.coordinate.x && grids.coordinate.y == g.coordinate.y) {
                if (grids.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean isAdjacent(Grid g1, Grid g2) {
        if (g1.coordinate.x == g2.coordinate.x && g1.coordinate.y == g2.coordinate.y) {
            return false;
        }
        int diffX = Math.abs((g1.coordinate.x - g2.coordinate.x) / SIZE);
        int diffY = Math.abs((g1.coordinate.y - g2.coordinate.y) / SIZE);
        return (diffX <= 1 && diffY <= 1);
    }

    private static boolean findAndChangeGridState(Point point, GridState gridState, boolean forcedChange) {
        for (Grid grid : Game.getGrids()) {
            if (grid.coordinate.x == point.x && grid.coordinate.y == point.y) {
                if (forcedChange) {
                    grid.forceChangeState(gridState);
                    return true;
                } else {
                    return grid.changeState(gridState);
                }

            }
        }
        return false;
    }

    private static boolean adjacentMoveAllowed(Grid g, GridState gridState) {

        List<Grid> adjacentGrids = findAdjacentGrids(g);
        for (Grid grid : adjacentGrids) {
            if (grid.state == gridState) {
                return true;
            }
        }

        return false;

    }

    private static List<Grid> findAdjacentGrids(Grid g1) {
        List<Grid> adjacentGrids = new ArrayList<>();
        for (Grid g2 : Game.getGrids()) {
            if (isAdjacent(g1, g2)) {
                adjacentGrids.add(g2);
            }
        }
        return adjacentGrids;
    }


    public static boolean isBlocked(GridState gridState) {
        for (Grid grid : Game.getGrids()) {
            if (grid.state == gridState) {
                List<Grid> adjacentGrids = findAdjacentGrids(grid);
                for (Grid g : adjacentGrids) {
                    if (g.state == GridState.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }




    public static String boardState() {

        int column = 0;
        int row =1;
        StringBuilder boardState = new StringBuilder(" 12345678910 \r\n" + row);
        for(Grid grid :Game.getGrids()){
            if (column==10){
                row++;
                boardState.append("\r\n").append(row);
                column = 0;
            }
            switch (grid.state){
                case EMPTY:
                    boardState.append(" ");
                    column++;
                    break;
                case OCCUPIED1:
                    boardState.append("R");
                    column++;
                    break;
                case OCCUPIED2:
                    boardState.append("B");
                    column++;
                    break;
                case OCCUPIED3:
                    boardState.append("G");
                    column++;
                    break;
                case OCCUPIED4:
                    boardState.append("P");
                    column++;
                    break;
                case OCCUPIED5:
                    boardState.append("Y");
                    column++;
                    break;


            }


        }
        return boardState.toString();
    }


    void draw(Graphics2D graphics2D) {
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, fontSize / 2));
        drawBoardMarkers(graphics2D);
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        drawGrids(graphics2D);
    }


    private void drawGrids(Graphics2D graphics2D) {
        for (Grid grid : grids) {
            switch (grid.state) {
                case OCCUPIED1:
                    drawGrid(graphics2D, grid, Color.RED, "R");
                    break;
                case OCCUPIED2:
                    drawGrid(graphics2D, grid, Color.BLUE, "B");
                    break;
                case OCCUPIED3:
                    drawGrid(graphics2D, grid, Color.GREEN, "G");
                    break;
                case OCCUPIED4:
                    drawGrid(graphics2D, grid, Color.PINK, "P");
                    break;
                case OCCUPIED5:
                    drawGrid(graphics2D, grid, Color.YELLOW, "Y");
                    break;
            }
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawRect(grid.coordinate.x, grid.coordinate.y, SIZE, SIZE);
        }
    }

    private void drawGrid(Graphics2D graphics2D, Grid grid, Color red, String r) {
        graphics2D.setColor(red);
        graphics2D.fillRect(grid.coordinate.x, grid.coordinate.y, SIZE, SIZE);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(r, grid.coordinate.x + (SIZE / 2 - fontSize / 3), grid.coordinate.y + (SIZE / 2 + fontSize / 3));
    }


    private void drawBoardMarkers(Graphics2D graphics2D) {
        //might be able to simplify

        int count = 1;
        for (int i = (START_GRID_POINT + SIZE / 2); i < ((COLUMN + 1) * SIZE); i += SIZE) {
            if (i < (ROW + 1) * SIZE) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(count + "", START_GRID_POINT / 2, i);
            }
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(count + "", i, START_GRID_POINT / 2);
            count++;
        }
    }
}
