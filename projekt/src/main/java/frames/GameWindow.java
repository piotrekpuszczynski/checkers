package frames;

import server.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    private JLabel state;
    public GameWindow(String playersAmount, String boardSize, int pawnsAmount, Client client) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this::exit);
        menuBar.add(exit);
        JMenuItem skipMove = new JMenuItem("skip move");
        skipMove.addActionListener(this::skipMove);
        menuBar.add(skipMove);
        setJMenuBar(menuBar);

        state = new JLabel("...");
        getContentPane().add(state, BorderLayout.SOUTH);

        add(new BoardPanel(playersAmount, boardSize, pawnsAmount, client));

        setVisible(true);
    }

    public void changeState(String string) { this.state.setText(string); }

    public void skipMove(ActionEvent e) {}
    public void exit(ActionEvent e) { System.exit(0); }

}
