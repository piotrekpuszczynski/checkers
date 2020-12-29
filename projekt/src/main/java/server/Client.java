package server;

import frames.GameWindow;
import layout.pawns.Pawn;

import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner in;
    PrintWriter out;
    Socket socket;
    Color color;
    GameWindow gameWindow;
    private boolean turn = false;

    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58989);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());
        color = new Color(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));

        gameWindow = new GameWindow(playersAmount, boardSize, pawnsAmount, this);
        play();
    }

    public Color getColor() { return this.color; }

    public void send(String data) { out.println(data); }

    public boolean getTurn() { return this.turn; }

    public void play() throws Exception {
        Pawn pawn = null;
        try {
            while (in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println(response);
                if (response.startsWith("REMOVE")) {
                    pawn = gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).getPawn();
                    gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).removePawn();
                } else if (response.startsWith("PUT")) {
                    gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).putPawn(pawn);
                    pawn = null;
                } else if (response.startsWith("MESSAGE")) {
                    gameWindow.changeState(response);
                    if (response.split(" ")[1].equals("Your")) turn = true;
                    else if (response.split(" ")[1].equals("Waiting")) turn = false;
                } else if (response.startsWith("SHOW")) {
                    //for (Field fields: mouse.get)
                }

                gameWindow.getBoard().getMouse().panel.repaint();
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
