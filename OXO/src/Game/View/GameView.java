package Game.View;

import Game.System.GameLogic;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    public GameView(String name,JPanel gamePanel,JPanel buttonPanel){
        setTitle(name);
        add(gamePanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        while (true){
            repaint();
            if(GameLogic.turn == (Math.pow(GameLogic.grid.length,2)+1)){
                if(GameLogic.player1Score>GameLogic.player2Score){
                    JOptionPane.showMessageDialog(this,
                            "Game Over\nPlayer 1 won" );
                    break;
                }
                else if(GameLogic.player1Score==GameLogic.player2Score){
                    JOptionPane.showMessageDialog(this,
                            "Game Over\nDraw" );
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(this,
                            "Game Over\nPlayer 2 won" );
                    break;
                }

            }
        }

    }
}
