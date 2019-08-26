package Game.System;

import Game.HelperClasses.Constants;
import Game.HelperClasses.GridState;
import Game.View.ButtonPanel;

public class GameLogic {
    public static GridState[][] grid;
    public static int gridSize;
    public static char move;
    public static int turn = 1;
    public static int player1Score, player2Score;

    public GameLogic() {
        grid = new GridState[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                grid[row][column] = GridState.EMPTY;
            }
        }
    }

    public static void cellClicked(int x, int y) {
        int row = y / (Constants.GRID_LENGTH / gridSize);
        int column = x / (Constants.GRID_LENGTH / gridSize);
        System.out.println("("+row+","+column+")");

        if (move == 'X' && grid[row][column] == GridState.EMPTY) {
            if (turn % 2 == 1) grid[row][column] = GridState.PLAYER1X;
            else grid[row][column] = GridState.PLAYER2X;
            turn++;
            updatePlayer(row, column);
        }
        if (move == 'O' && grid[row][column] == GridState.EMPTY) {
            if (turn % 2 == 1) grid[row][column] = GridState.PLAYER1O;
            else grid[row][column] = GridState.PLAYER2O;
            turn++;
            updatePlayer(row, column);
        }
        move = 'E';


    }

    private static void updatePlayer(int row, int column) {
        if (turn % 2 == 0) {
            ButtonPanel.label.setText("Player 2's turn");
            //Was player 1s turn
            player1Score +=  checkForOXO(row, column);
        } else {
            ButtonPanel.label.setText("Player 1's turn");
            //Was player 2s turn
            player2Score +=  checkForOXO(row, column);
        }
        String s =  "Player 1 : "+player1Score+"\t\t\t\t\tPlayer 2 : "+player2Score;
        s = s.replaceAll("\t", "    ");
        ButtonPanel.score.setText(s);
    }

    private static int checkForOXO(int row, int column) {
        int score = 0;
        if (column > 0 && column < grid.length-1) {
            //TODO: check horizontal middle
            if((grid[row][column-1]==GridState.PLAYER1O  || grid[row][column-1]==GridState.PLAYER2O)  &&
                    (grid[row][column]==GridState.PLAYER1X || grid[row][column]==GridState.PLAYER2X)  &&
                    (grid[row][column+1]==GridState.PLAYER1O || grid[row][column+1]==GridState.PLAYER2O)
            ){
                score++;
            }
        }
        if (row > 0 && row < grid.length-1) {
            //TODO: check vertical middle
            if((grid[row-1][column]==GridState.PLAYER1O  || grid[row-1][column]==GridState.PLAYER2O)  &&
                    (grid[row][column]==GridState.PLAYER1X || grid[row][column]==GridState.PLAYER2X)  &&
                    (grid[row+1][column]==GridState.PLAYER1O || grid[row+1][column]==GridState.PLAYER2O)
            ){
                score++;
            }
        }

        if (column > 1) {
            //TODO: check left
            if((grid[row][column-2]==GridState.PLAYER1O  || grid[row][column-2]==GridState.PLAYER2O)  &&
                    (grid[row][column-1]==GridState.PLAYER1X || grid[row][column-1]==GridState.PLAYER2X)  &&
                    (grid[row][column]==GridState.PLAYER1O || grid[row][column]==GridState.PLAYER2O)
            ){
                score++;
            }

        }
        if (column < grid.length - 2) {
            //TODO: check right
            if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                    (grid[row][column+1]==GridState.PLAYER1X || grid[row][column+1]==GridState.PLAYER2X)  &&
                    (grid[row][column+2]==GridState.PLAYER1O || grid[row][column+2]==GridState.PLAYER2O)
            ){
                score++;
            }

        }

        if (row > 1) {
            //TODO: check up
            if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                    (grid[row-1][column]==GridState.PLAYER1X || grid[row-1][column]==GridState.PLAYER2X)  &&
                    (grid[row-2][column]==GridState.PLAYER1O || grid[row-2][column]==GridState.PLAYER2O)
            ){
                score++;
            }

            if (column > 1) {
                //TODO: check up left
                if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                        (grid[row-1][column-1]==GridState.PLAYER1X || grid[row-1][column-1]==GridState.PLAYER2X)  &&
                        (grid[row-2][column-2]==GridState.PLAYER1O || grid[row-2][column-2]==GridState.PLAYER2O)
                ){
                    score++;
                }

            }

            if (column < grid.length - 2) {
                //TODO: check up right
                if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                        (grid[row-1][column+1]==GridState.PLAYER1X || grid[row-1][column+1]==GridState.PLAYER2X)  &&
                        (grid[row-2][column+2]==GridState.PLAYER1O || grid[row-2][column+2]==GridState.PLAYER2O)
                ){
                    score++;
                }

            }

        }
        if (row < grid[0].length - 2) {
            //TODO: check down
            if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                    (grid[row+1][column]==GridState.PLAYER1X || grid[row+1][column]==GridState.PLAYER2X)  &&
                    (grid[row+2][column]==GridState.PLAYER1O || grid[row+2][column]==GridState.PLAYER2O)
            ){
                score++;
            }

            if (column > 1) {
                //TODO: check down-left
                if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                        (grid[row+1][column-1]==GridState.PLAYER1X || grid[row+1][column-1]==GridState.PLAYER2X)  &&
                        (grid[row+2][column-2]==GridState.PLAYER1O || grid[row+2][column-2]==GridState.PLAYER2O)
                ){
                    score++;
                }
            }

            if (column < grid.length - 2) {
                //TODO: check down-right
                if((grid[row][column]==GridState.PLAYER1O  || grid[row][column]==GridState.PLAYER2O)  &&
                        (grid[row+1][column+1]==GridState.PLAYER1X || grid[row+1][column+1]==GridState.PLAYER2X)  &&
                        (grid[row+2][column+2]==GridState.PLAYER1O || grid[row+2][column+2]==GridState.PLAYER2O)
                ){
                    score++;
                }
            }
        }
        return score;
    }

    public GridState[][] getGrid() {
        return grid;
    }
}
