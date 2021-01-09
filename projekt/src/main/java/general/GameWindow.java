package general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    private final JLabel state;
    private final Facade facade;

    /**
     * ustawia zawartosc okna gry
     * @param playersAmount liczba graczy
     * @param boardSize wielksoc planszy
     * @param pawnsAmount liczba pionkow
     */
    public GameWindow(String playersAmount, String boardSize, int pawnsAmount, Facade facade) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        facade.setGameWindow(this);
        this.facade = facade;

        JMenuBar menuBar = new JMenuBar();
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this::exit);
        menuBar.add(exit);
        JMenuItem skipMove = new JMenuItem("skip move / confirm move");
        skipMove.addActionListener(this::skipMove);
        menuBar.add(skipMove);
        JMenuItem color = new JMenuItem("Your pawns' color");
        color.setBackground(facade.getClient().getColor());
        menuBar.add(color);
        setJMenuBar(menuBar);

        state = new JLabel("MESSAGE Waiting for opponents");
        getContentPane().add(state, BorderLayout.SOUTH);


        add(new BoardPanel(playersAmount, boardSize, pawnsAmount, facade));

        setVisible(true);
    }

    /**
     * @param string zmienia stan dla klienta
     */
    public void changeState(String string) { this.state.setText(string); }

    /**
     * @param e pomijanie ruchu
     */
    public void skipMove(ActionEvent e) {
        if (facade.getClient().getTurn()) {
            if (facade.getPawn() != null) {
                facade.getLastField().putPawn(facade.getPawn());
                facade.getPawn().changePawnState();
                facade.setPawn(null);
                facade.resetAvailability();
                facade.getClient().send("PUT " + facade.getFieldIndex(facade.getLastField()));
            } else {
                facade.resetAvailability();
                facade.getClient().send("MESSAGE Your move");
            }
            facade.getMouse().nextMove = false;
        }
    }

    /**
     * @param e wyjscie
     */
    public void exit(ActionEvent e) { System.exit(0); }

}
