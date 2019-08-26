package Application.Menus;

import Application.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static int numOfPlayers = 0;
    private static JLabel connectedPlayers = new JLabel("Connected players: " + numOfPlayers);
    public static JLabel serverNotice = new JLabel("Pick Mode");


    public Frame(GamePanel game, String title) {
        super(title);
        JPanel bottomScreen = new JPanel();
        JPanel upperScreen = new JPanel();
        upperScreen.add(serverNotice);
        bottomScreen.add(connectedPlayers);
        this.add(upperScreen, BorderLayout.NORTH);
        this.add(game, BorderLayout.CENTER);
        this.add(bottomScreen, BorderLayout.SOUTH);
        this.setFocusable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void playerJoined() {
        numOfPlayers++;
        //GameService.playerIDs.put(GridState.createState("OCCUPIED" + numOfPlayers), "");
        connectedPlayers.setText("Connected players: " + numOfPlayers);
    }

    public void playerLeft() {
        numOfPlayers--;
        connectedPlayers.setText("Connected players: " + numOfPlayers);
    }
}


