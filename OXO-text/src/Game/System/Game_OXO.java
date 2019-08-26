package Game.System;

import java.util.Scanner;

public class Game_OXO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size(must be above 2 ");

        String size;
        while (true) {                                   //validates size of board
            System.out.print(">");
            size = scanner.next();                   //gets size
            try {
                int s = Integer.parseInt(size);
                if (s < 3) throw new Exception();
                break;
            } catch (Exception ignored) {
                System.out.println();
            }
        }


        GameLogic.gridSize = Integer.parseInt(size);
        GameLogic gameLogic = new GameLogic();        //deals with game's back end processes


        String move;                                   //stores which move currently wants to be placed
        while (GameLogic.turn < Math.pow(GameLogic.gridSize, 2) + 1) {  //check if the game is over or not.
            gameLogic.showBoard();                              //prints board to console
            gameLogic.getTurn();                                //prints player turn
            move = scanner.next();                              //Prompts user for move input
            while (!move.equals("O") && !move.equals("X")) {      //Validates move inputted
                System.out.println("Incorrect move");
                gameLogic.getTurn();
                move = scanner.next().toUpperCase();

            }

            int row;                                            //stores row starting at 1 and ending at the number of rows
            int column;                                         //stores column starting at 1 and ending at the number of columns
            while (true) {
                System.out.println("Enter row and column(separated by comma)");
                String coordinates = scanner.next();                            //gets co-ordinates in format  1,2
                String[] cords = coordinates.split(",");               // splits input into list
                try {
                    row = Integer.parseInt(cords[0]);
                    column = Integer.parseInt(cords[1]);
                    if (row > 0 && column > 0 && row <= GameLogic.grid.length && column <= GameLogic.grid[0].length) {              //checks if row is in correct range
                        if (!(GameLogic.isEmptyGrid(row, column))) {                                      //check if player is trying to insert into full list
                            System.out.println("Co-ordinates isn't empty");
                            continue;
                        }
                        break;
                    } else {
                        System.out.println("Row or column value is out of bounds");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect format");
                }
            }
            GameLogic.updateBoard(row, column, move);            //updates board and player score
            GameLogic.turn++;                                  //increases turn
        }
        GameLogic.showWinner();                                 //prints winner
    }


}
