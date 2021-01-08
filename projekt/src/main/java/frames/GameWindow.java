package frames;

import server.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    private final JLabel state;
    private final Client client;
    private final BoardPanel board;

    /**
     * ustawia zawartosc okna gry
     * @param playersAmount liczba graczy
     * @param boardSize wielksoc planszy
     * @param pawnsAmount liczba pionkow
     * @param client klient
     */
    public GameWindow(String playersAmount, String boardSize, int pawnsAmount, Client client) {
        super("Checkers");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this::exit);
        menuBar.add(exit);
        JMenuItem skipMove = new JMenuItem("skip move / confirm move");
        skipMove.addActionListener(this::skipMove);
        menuBar.add(skipMove);
        JMenuItem color = new JMenuItem("Your pawns' color");
        color.setBackground(client.getColor());
        menuBar.add(color);
        setJMenuBar(menuBar);

        state = new JLabel("MESSAGE Waiting for opponents");
        getContentPane().add(state, BorderLayout.SOUTH);

        board = new BoardPanel(playersAmount, boardSize, pawnsAmount, this);
        add(board);

        setVisible(true);
    }

    /**
     * @return zwraca klienta
     */
    public Client getClient() { return this.client; }

    /**
     * @return zwraca panel
     */
    public BoardPanel getBoard() { return this.board; }

    /**
     * @param string zmienia stan dla klienta
     */
    public void changeState(String string) { this.state.setText(string); }

    /**
     * @param e pomijanie ruchu
     */
    public void skipMove(ActionEvent e) {
        if (client.getTurn()) {
            if (board.getMouse().getPawn() != null) {
                board.getMouse().getLastField().putPawn(board.getMouse().getPawn());
                board.getMouse().getPawn().changePawnState();
                board.getMouse().setPawn(null);
                board.getMouse().resetAvailability();
                client.send("PUT " + board.getMouse().getFieldIndex(board.getMouse().getLastField()));
            } else {
                board.getMouse().resetAvailability();
                client.send("MESSAGE Your move");
            }
            board.getMouse().nextMove = false;
        }
    }

    /**
     * @param e wyjscie
     */
    public void exit(ActionEvent e) { System.exit(0); }

}
