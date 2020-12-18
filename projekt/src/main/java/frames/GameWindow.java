package frames;

import javax.swing.*;

public class GameWindow extends JFrame {
    GameWindow(int amount) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        add(new GamePanel(amount));

        setVisible(true);
    }

}
