package Game.System;

class GameLogic {
    static GridState[][] grid;
    static int gridSize;
    static int turn = 1;
    private static int player1Score, player2Score;

    GameLogic() {                           //instantiates the grid/board with empty spots
        grid = new GridState[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                grid[row][column] = GridState.EMPTY;
            }
        }
    }


    static void updateBoard(int row, int column, String move) {         //changes board positions and checks for OXO
        if (move.equals("O")) grid[row - 1][column - 1] = GridState.O;
        else grid[row - 1][column - 1] = GridState.X;
        System.out.println(turn);

        if (turn % 2 == 0) {
            System.out.println("Player 2's turn");
            //Was player 1s turn
            player2Score += checkForOXO(row - 1, column - 1);

        } else {
            System.out.println("Player 1's turn");
            //Was player 2s turn
            player1Score += checkForOXO(row - 1, column - 1);

        }
        System.out.println("Player 1 : " + player1Score + "\t\t\t\t\tPlayer 2 : " + player2Score);
    }

    static boolean isEmptyGrid(int row, int column) {               //checks if a spot is empty or occupied
        return grid[row - 1][column - 1] == GridState.EMPTY;
    }


    private static int checkForOXO(int row, int column) {   //makes sure only necessary checks are made for OXO
        int score = 0;
        if (column > 0 && column < grid.length - 1) {
            //check horizontal-middle
            score += getScore(row, column - 1, row, column, row, column + 1);
        }
        if (row > 0 && row < grid.length - 1) {
            //check vertical-middle
            score += getScore(row - 1, column, row, column, row + 1, column);
        }

        if (column > 1) {
            //check left
            score += getScore(row, column - 2, row, column - 1, row, column);


        }
        if (column < grid.length - 2) {
            //check right
            score += getScore(row, column, row, column + 1, row, column + 2);
        }

        if (row > 1) {
            //check up
            score += getScore(row, column, row - 1, column, row - 2, column);

            if (column > 1) {
                //checks up-left
                score += getScore(row, column, row - 1, column - 1, row - 2, column - 2);
            }

            if (column < grid.length - 2) {
                //checks up-right
                score += getScore(row, column, row - 1, column + 1, row - 2, column + 2);

            }

        }
        if (row < grid[0].length - 2) {
            //checks down
            score += getScore(row, column, row + 1, column, row + 2, column);


            if (column > 1) {
                //checks down-left
                score += getScore(row, column, row + 1, column - 1, row + 2, column - 2);
            }

            if (column < grid.length - 2) {
                //checks down-right
                score += getScore(row, column, row + 1, column + 1, row + 2, column + 2);
            }
        }
        return score;
    }


    private static int getScore(int row1, int col1, int row2, int col2, int row3, int col3) {           //checks a lane for OXO
        if (grid[row1][col1] == GridState.O && grid[row2][col2] == GridState.X && grid[row3][col3] == GridState.O) {
            return 1;
        }
        return 0;

    }

    static void showWinner() {                      //prints winner
        System.out.println("GAME OVER");
        if (player1Score > player2Score) {
            System.out.println("Player 1 wins");
        } else if (player1Score < player2Score) {
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Draw");
        }
    }

    void showBoard() {//displays board  . for empty,O and X for respective moves
        for (GridState[] gridStates : grid) {
            for (int column = 0; column < grid[0].length; column++) {
                if (gridStates[column] == GridState.EMPTY) {
                    System.out.print(". ");
                } else if (gridStates[column] == GridState.O)
                    System.out.print("O ");
                else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    void getTurn() {                    //prints player turn
        if (turn % 2 == 0) System.out.println("Player 2's turn, O or X > ");
        else System.out.println("Player 1's turn, O or X > ");
    }

    public enum GridState {                 //states of cells
        EMPTY, X, O
    }
}
