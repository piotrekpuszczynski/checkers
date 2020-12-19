package frames;

import javax.swing.*;

public class GameWindow extends JFrame {
    GameWindow(int playersAmount, String boardSize, int pawnsAmount) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        add(new GamePanel(playersAmount, boardSize, pawnsAmount));

        setVisible(true);
    }

}
