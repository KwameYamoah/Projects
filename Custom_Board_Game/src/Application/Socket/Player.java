package Application.Socket;

import Application.GridState;


import java.net.Socket;


public class Player {
    private String name;
    private GridState playerID;
    private boolean login;
    private Socket socket;

    int getScore() {
        return score;
    }

    private int score;

    Player(Socket socket) {
        name = "";
        playerID = null;
        login = true;
        this.socket = socket;

    }

    Player(String name, GridState id, Socket socket) {
        this.name = name;
        playerID = id;
        this.socket = socket;
        login = true;
        score = 0;
    }

    boolean isLogin() {
        return login;
    }

    void setLogin() {
        this.login = false;
    }

    GridState getPlayerID() {
        return playerID;
    }

    String getName() {
        return name;
    }

    Socket getSocket() {
        return socket;
    }

    void incScore() {
        score = score + 1;
    }

    void closeSocket() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Error  closing socket");
        }
    }

    @Override
    public String toString() {
        return name + " Score - " + score;
    }
}
