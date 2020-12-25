package frames;

import server.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    private final JLabel state;
    private final Client client;
    public GameWindow(String playersAmount, String boardSize, int pawnsAmount, Client client) {
        super("Checkers");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this::exit);
        menuBar.add(exit);
        JMenuItem skipMove = new JMenuItem("skip move");
        skipMove.addActionListener(this::skipMove);
        menuBar.add(skipMove);
        JMenuItem color = new JMenuItem("Your pawn's color");
        color.setBackground(client.getColor());
        menuBar.add(color);
        setJMenuBar(menuBar);

        state = new JLabel("MESSAGE Waiting for opponent to move");
        getContentPane().add(state, BorderLayout.SOUTH);

        add(new BoardPanel(playersAmount, boardSize, pawnsAmount, client));

        setVisible(true);
    }

    public void changeState(String string) { this.state.setText(string); }

    public void skipMove(ActionEvent e) {client.send("MESSAGE Your");}
    public void exit(ActionEvent e) { System.exit(0); }

}
