package server;

import frames.GameWindow;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner in;
    PrintWriter out;

    public Client(String serverAddress) throws Exception {

        Socket socket = new Socket(serverAddress, 58989);
        Scanner s = new Scanner(System.in);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());

        new GameWindow(playersAmount, boardSize, pawnsAmount, this);
    }

    public void send(String data) {
        out.println(data);
    }
}
