package server;

import frames.GameWindow;
import frames.mouse.MoveAdapter;
import layout.pawns.Pawn;

import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner in;
    PrintWriter out;
    Socket socket;
    MoveAdapter mouse;
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

    public void setMouse(MoveAdapter mouse) { this.mouse = mouse; }

    public boolean getTurn() { return this.turn; }

    public void play() throws Exception {
        Pawn pawn = null;
        try {
            while (in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println(response);
                if (response.startsWith("REMOVE")) {
                    pawn = mouse.getField(Integer.parseInt(response.split(" ")[1])).getPawn();
                    mouse.getField(Integer.parseInt(response.split(" ")[1])).removePawn();
                    mouse.panel.repaint();
                } else if (response.startsWith("PUT")) {
                    mouse.getField(Integer.parseInt(response.split(" ")[1])).putPawn(pawn);
                    mouse.panel.repaint();
                    //gameWindow.changeState(in.nextLine());
                } else if (response.startsWith("MESSAGE")) {
                    gameWindow.changeState(response);
                    if (response.split(" ")[1].equals("Your")) turn = true;
                    else if (response.split(" ")[1].equals("Waiting")) turn = false;
                //} else if (response.startsWith("VICTORY")) {
                //    JOptionPane.showMessageDialog(frame, "Winner Winner");
                //    break;
                //} else if (response.startsWith("DEFEAT")) {
                //    JOptionPane.showMessageDialog(frame, "Sorry you lost");
                //    break;
                //} else if (response.startsWith("TIE")) {
                //    JOptionPane.showMessageDialog(frame, "Tie");
                //    break;
                //} else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                //    JOptionPane.showMessageDialog(frame, "Other player left");
                //    break;
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
