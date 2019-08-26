package Game.View;

import Game.HelperClasses.Constants;
import Game.HelperClasses.GridState;
import Game.HelperClasses.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private  GridState [][] grid;
    private int cellSize ;
    public GamePanel(GridState [][] grid){
        this.grid = grid;
        cellSize = Constants.GRID_LENGTH /grid.length;
        addMouseListener(new MouseHandler());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int column = 0; column < grid.length; column++){
            for(int row = 0; row  < grid.length; row ++){
                g.drawRect(column*cellSize,row*cellSize,cellSize,cellSize);
            }
        }
        int fontSize = 100;
        g.setFont(new Font("Courier", Font.BOLD,fontSize));
        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column  < grid.length; column ++) {
                if(grid[row][column]==GridState.PLAYER1X || grid[row][column]==GridState.PLAYER2X ){
                    if(grid[row][column]==GridState.PLAYER1X) g.setColor(Color.RED);
                    else g.setColor(Color.BLUE);
                    g.drawString("X",(column*cellSize)+(cellSize/2)-(fontSize/3),(row*cellSize)+(fontSize/9)*8);
                }
                else if(grid[row][column]==GridState.PLAYER1O || grid[row][column]==GridState.PLAYER2O){
                    if(grid[row][column]==GridState.PLAYER1O) g.setColor(Color.RED);
                    else g.setColor(Color.BLUE);
                    g.drawString("O",(column*cellSize)+(cellSize/2)-(fontSize/3),(row*cellSize)+(fontSize/9)*8);
                }
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.GRID_LENGTH +1,Constants.GRID_LENGTH +1);
    }

}
