package Application.Socket;

import Application.*;
import Application.Ai.AICreator;
import Application.Menus.Frame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import java.util.concurrent.atomic.AtomicInteger;

import static Application.Constants.*;

public class GameServer {
    static Game game;
    static Frame frame;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        AtomicInteger count = new AtomicInteger(0);
        game = new Game();
        GamePanel gamePanel = new GamePanel(game);
        frame = new Frame(gamePanel, "Game");
        GameService service = new GameService();
        ServerSocket server = new ServerSocket(PORT);

        /*
        int serverSize;
        int aiS;

        if(args.length==0){
            serverSize = 2;
            aiS = 2;
        }
        else {
            try {
                serverSize = Integer.parseInt(args[0]);
                aiS = Integer.parseInt(args[1]);
            }
            catch (Exception e){
                serverSize = 2;
                aiS = 1;
            }

        }
        if(aiS>serverSize){
            serverSize = 2;
            aiS = 2;
        }
        */

         //for running in intellij (not useful for jar)
        Scanner s = new Scanner(System.in);
        System.out.println("Enter size of this server ");
        int serverSize = s.nextInt();
        while (serverSize < 2) {
            System.out.println("Server size must be at least 2");
            System.out.println("Enter size of this server ");
            serverSize = s.nextInt();
            System.out.println();
        }

        System.out.println("Enter number of AIs for this server ");  // Convenience to create AIs(They still act like players)
        int aiS = s.nextInt();
        while (aiS > serverSize) {
            System.out.println("Number of AI should be less than  server size");
            System.out.println("Enter number of AIs for this server ");
            aiS = s.nextInt();
            System.out.println();
        }


        System.out.println("Started GameServer at port " + PORT);
        System.out.println("Waiting for players to connect...");

        String[] arg = {aiS + ""};
        AICreator.main(arg);
        frame.repaint();

        do {
            Socket socket = server.accept();
            System.out.println("Player connected.");
            if (!GameService.gameStarted) {
                service.addPlayer(socket, count.addAndGet(1));
            } else {
                service.addSpectator(socket);
            }
            service.broadcast(GameService.players, serverSize + " players must join for the game to start" + "\r\n");
            if (count.get() > serverSize - 1 && !GameService.gameStarted) {
                service.broadcast(GameService.players, "Game starting shortly" + "\r\n");
                new Thread(service).start();
                GameService.gameStarted = true;
            }
            frame.repaint();


        } while (game.notGameOver());
    }
}



