package Application.Socket;

import Application.Board;
import Application.Game;
import Application.Grid;
import Application.GridState;
import Application.Menus.Frame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameService implements Runnable {
    private Scanner in;
    private PrintWriter out;
    private static Game game;
    private static int turn = 1;
    private int doubleCardUsage = 0;
    private int freedomCardUsage = 0;
    private int replacementCardUsage = 0;
    private static Frame frame;
    static boolean gameStarted = false;
    static ArrayList<Player> players = new ArrayList<>();
    private static CopyOnWriteArrayList<Player> spectators = new CopyOnWriteArrayList<>();

    GameService() {
        game = GameServer.game;
        frame = GameServer.frame;
    }


    void addPlayer(Socket socket, int id) {
        if (!gameStarted) {
            String playerName = color(id);
            Player p = new Player(playerName, GridState.createState("OCCUPIED" + id), socket);
            players.add(p);
            game.placeStartStone(p.getPlayerID());
            frame.playerJoined();
        }
    }

    void addSpectator(Socket socket) {
        spectators.add(new Player(socket));
    }


    public void run() {
        welcome(players);
        broadcast(players, Board.boardState());
        while (game.notGameOver()) {
            Player player = players.get(turn - 1);
            if (Board.isBlocked(player.getPlayerID())) {
                System.out.println(player.getName() + " is blocked");
                turn = (turn + 1) % (players.size() + 1);
                if (turn == 0) {
                    turn = 1;
                }
            } else {
                while (player.isLogin()) {
                    try {
                        Socket socket = player.getSocket();
                        try {
                            in = new Scanner(socket.getInputStream());
                            out = new PrintWriter(socket.getOutputStream(), true);
                        } catch (IOException e) {
                            System.out.println("Error trying to get socket from online player");
                        }
                        out.println("It is your turn" + "\r\n");
                        Move move = Move.parse(in.nextLine());
                        String response = execute(game, move, player);
                        out.println(response + "\r\n");
                        if (!(response.equals("Move rejected") || response.equals("Invalid") || response.equals("You are out of double card")
                                || response.equals("You are out of freedom card") || response.equals("You are out of replacement card"))) {

                            turn = (turn + 1) % (players.size() + 1);
                            broadcast(players, Board.boardState());
                        }
                        if (turn == 0) {
                            turn = 1;
                        }
                        break;
                    } catch (NoSuchElementException e) {
                        player.setLogin();
                    }
                }
            }
        }
        broadcast(players, scoreboard() + "\r\n");
        for (Player player : players) disconnect(player);
        gameStarted = false;
    }

    String color(int turn) {
        switch (turn) {
            case 1:
                return "Red";
            case 2:
                return "Blue";
            case 3:
                return "Green";
            case 4:
                return "Pink";
            case 5:
                return "Yellow";


        }
        return "red";

    }

    void broadcast(ArrayList<Player> players, String broadcastMessage) {
        PrintWriter out;

        for (Player s : players) {
            try {
                out = new PrintWriter(s.getSocket().getOutputStream(), true);
                out.println(broadcastMessage + "\r\n");

            } catch (IOException e) {
                System.out.println("Error getting socket from players at broadcast method");
            }
        }

    }

    private void welcome(ArrayList<Player> players) {
        PrintWriter out;
        for (Player s : players) {
            try {
                out = new PrintWriter(s.getSocket().getOutputStream(), true);
                out.println("Welcome - you may make a move when it your turn!" + "\r\n");
                out.println("Your tag is " + s.getName() + "\r\n");
            } catch (IOException e) {
                System.out.println("Error getting socket from players at welcome method");
            }
        }

    }

    private void broadcastOther(ArrayList<Player> players, Player player, String broadcastMessage) {
        PrintWriter out;
        PrintWriter outStream;
        for (Player s : players) {
            try {
                if (s != player) {

                    out = new PrintWriter(s.getSocket().getOutputStream(), true);
                    out.println(broadcastMessage + "\r\n");

                }
                if (spectators.size() > 0) {

                    for (Player spectator : spectators) {
                        outStream = new PrintWriter(spectator.getSocket().getOutputStream(), true);
                        outStream.println(broadcastMessage + "\r\n");
                        outStream.println(Board.boardState() + "\r\n");
                    }

                }
            } catch (IOException e) {
                System.out.println("Error getting socket at broadcastOther");
            }
        }

    }

    Player findPlayer(GridState state) {
        for (Player player : players) {
            if (player.getPlayerID() == state) return player;
        }
        return null;
    }


    private String scoreboard() {
        for (Grid grid : Game.getGrids()) {
            Player player = null;
            switch (grid.state) {
                case OCCUPIED1:
                    player = findPlayer(GridState.OCCUPIED1);
                    if (player != null)
                        player.incScore();
                    break;
                case OCCUPIED2:
                    player = findPlayer(GridState.OCCUPIED2);
                    if (player != null)
                        player.incScore();
                    break;

                case OCCUPIED3:
                    player = findPlayer(GridState.OCCUPIED3);
                    if (player != null)
                        player.incScore();
                    break;
                case OCCUPIED4:
                    player = findPlayer(GridState.OCCUPIED4);
                    if (player != null)
                        player.incScore();
                    break;
                case OCCUPIED5:
                    player = findPlayer(GridState.OCCUPIED5);
                    if (player != null)
                        player.incScore();
                    break;
            }

            if (player != null) {
                System.out.println(player.getName());
            }


        }

        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getScore() >= winner.getScore()) {
                winner = player;
            }
        }

        StringBuilder scoreBoardText = new StringBuilder("Winner - " + winner.getName() + " Score - " + winner.getScore() + "\n");
        for (Player player : players) {
            if (player != winner) {
                scoreBoardText.append(player);
                scoreBoardText.append("\n");
            }

        }

        return scoreBoardText.toString();

    }

    private void disconnect(Player player) {
        System.out.println(player.getName() + " has disconnected" + "\r\n");
        frame.playerLeft();
        try {
            player.closeSocket();
        } catch (Exception e) {
            System.out.println("Error closing player sockets at disconnect");
        }
    }


    public String execute(Game game, Move move, Player player) {
        try {
            Thread.sleep(500);
            switch (move.cardType) {
                case NO_CARD:
                    if (game.changeState(move, player.getPlayerID())) {
                        Frame.serverNotice.setText(player.getName() + " has set a stone");
                        broadcastOther(players, player, player.getName() + " has set a stone");
                        return "You have set a stone";
                    }
                    return "Move rejected";
                case DOUBLE_CARD:
                    if (doubleCardUsage < 1) {
                        if (game.changeState(move, player.getPlayerID())) {
                            doubleCardUsage++;
                            Frame.serverNotice.setText(player.getName() + " has activate a DOUBLE! card");
                            broadcastOther(players, player, player.getName() + " has activate a DOUBLE! card");
                            return "You have used a DOUBLE card";
                        }
                    } else {
                        return "You are out of double card";
                    }
                    return "Move rejected";
                case FREEDOM_CARD:

                    if (freedomCardUsage < 1) {
                        if (game.changeState(move, player.getPlayerID())) {
                            freedomCardUsage++;
                            Frame.serverNotice.setText(player.getName() + " has  activate a FREEDOM! card");
                            broadcastOther(players, player, player.getName() + " has  activate a FREEDOM! card");
                            return "You have used a freedom card";
                        }
                    } else {
                        return "You are out of freedom card";
                    }
                    return "Move rejected";

                case REPLACEMENT_CARD:
                    if (replacementCardUsage < 1) {
                        if (game.changeState(move, player.getPlayerID())) {
                            replacementCardUsage++;
                            Frame.serverNotice.setText(player.getName() + " has  activate a REPLACEMENT! card");
                            broadcastOther(players, player, player.getName() + " has  activate a REPLACEMENT! card");

                            return "You have used a replacement card";
                        }
                    } else {
                        return "You are out of replacement card";
                    }
                    return "Move rejected";
                case INVALID:
                    return "Invalid";
                case EXIT:
                    player.setLogin();
                    return "Goodbye!";

            }
        } catch (Exception e) {
            System.out.println("Error trying to impose delay between movement at execute");
        }


        return "";
    }


}
