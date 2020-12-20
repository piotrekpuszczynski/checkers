package frames;

import javax.swing.*;

public class GameWindow extends JFrame {
    GameWindow(String playersAmount, String boardSize, int pawnsAmount) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(new BoardPanel(playersAmount, boardSize, pawnsAmount));

        setVisible(true);
    }

}
