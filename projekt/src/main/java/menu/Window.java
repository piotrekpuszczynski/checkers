package menu;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        super("Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        setLayout(new GridBagLayout());

        String[] amount = {"2", "3", "4", "6"};
        JComboBox amountOfPlayers = new JComboBox(amount);

        add(new JLabel("Wybierz liczbe graczy: "));
        add(amountOfPlayers);
    }
}