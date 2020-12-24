package server;

import frames.GameWindow;
import frames.mouse.MoveAdapter;
import layout.pawns.Pawn;

import javax.swing.*;
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

    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58989);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());
        color = new Color(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));
        System.out.println(color);

        new GameWindow(playersAmount, boardSize, pawnsAmount, this);
        play();
    }

    public Color getColor() { return this.color; }

    public void send(String data) {
        out.println(data);
    }

    public void setMouse(MoveAdapter mouse) { this.mouse = mouse; }

    public void play() throws Exception {
        Pawn pawn = null;
        try {
            var response = in.nextLine();
            //var mark = response.charAt(8);
            //var opponentMark = mark == '0' ? '1' : '0';
            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println(response);
                if (response.startsWith("REMOVE")) {
                    pawn = mouse.getField(Integer.parseInt(response.split(" ")[1])).getPawn();
                    mouse.getField(Integer.parseInt(response.split(" ")[1])).removePawn();
                    mouse.panel.repaint();
                } else if (response.startsWith("PUT")) {
                    mouse.getField(Integer.parseInt(response.split(" ")[1])).putPawn(pawn);
                    mouse.panel.repaint();
                //} else if (response.startsWith("MESSAGE")) {
                //    messageLabel.setText(response.substring(8));
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
