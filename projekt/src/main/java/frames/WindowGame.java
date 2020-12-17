package frames;

import javax.swing.*;

public class WindowGame extends JFrame {
    WindowGame(int amount) {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);

        setVisible(true);
    }
}
