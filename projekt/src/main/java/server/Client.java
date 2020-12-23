package server;

import frames.GameWindow;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client(String serverAddress) throws Exception {

        Socket socket = new Socket(serverAddress, 58989);
        Scanner s = new Scanner(System.in);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());

        new GameWindow(playersAmount, boardSize, pawnsAmount);
    }
}
