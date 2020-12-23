package frames;

import server.Client;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(String playersAmount, String boardSize, int pawnsAmount, Client client) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(new BoardPanel(playersAmount, boardSize, pawnsAmount, client));

        setVisible(true);
    }

}
