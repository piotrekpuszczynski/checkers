package frames;

import javax.swing.*;

public class GameWindow extends JFrame {
    GameWindow(int amount) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);

        add(new GamePanel(amount));

        setVisible(true);
    }

}