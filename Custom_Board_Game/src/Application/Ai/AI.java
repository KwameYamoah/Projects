package Application.Ai;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static Application.Constants.*;

class AI {

    private Scanner in;
    private PrintWriter out;


    AI() {
        try {
            Socket socket = new Socket(SERVER, PORT);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Connecting to AI's socket failed");

        }

    }

    void sendCommand(String command) {
        //StringBuilder result = new StringBuilder();
        out.println(command);
        /*
        while (true) {
            String line = in.nextLine();
            if (line.isEmpty()) break;
            result.append(line).append("\n");
        }
        return result.toString();
        */

    }

    String read() {
        StringBuilder result = new StringBuilder();
        String line;
        while (true) {
            try {
                line = in.nextLine();
            } catch (Exception e) {
                break;
            }
            if (line.isEmpty()) break;
            result.append(line).append("\n");
        }
        return result.toString();
    }


}
