package Application;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private Game game;
    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D graphics2D = (Graphics2D) g0;
        synchronized (Game.class) {
            game.board.draw(graphics2D);
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
