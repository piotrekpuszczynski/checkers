package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class SelectionWindow extends JFrame {

    public JComboBox<String> amountOfPlayers;

    public SelectionWindow() {
        super("Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width/3, getToolkit().getScreenSize().height/3);
        setLayout(new GridBagLayout());

        String[] amount = {"2", "3", "4", "6"};
        amountOfPlayers = new JComboBox<>(amount);

        add(new JLabel("Select amount of players: "));
        add(amountOfPlayers);

        Button ok = new Button("OK");
        ok.addActionListener(this::ok);
        add(ok);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void ok(ActionEvent e) {
        setVisible(false);
        new GameWindow(getAmount());
    }

    public int getAmount() {
        return Integer.parseInt(Objects.requireNonNull(amountOfPlayers.getSelectedItem()).toString());
    }
}