package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class SelectionWindow extends JFrame {

    private final JComboBox<String> playersAmount;
    private final JComboBox<String> boardSize;
    private final JComboBox<String> pawnsAmount;

    public SelectionWindow() {
        super("Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width/3, getToolkit().getScreenSize().height/3);
        setLayout(new GridLayout(4, 2));

        String[] amount = {"2", "3", "4", "6"};
        playersAmount = new JComboBox<>(amount);

        add(new JLabel("Select amount of players: "));
        add(playersAmount);

        String[] sizes = {"standard", "+1"};
        boardSize = new JComboBox<>(sizes);

        add(new JLabel("Select board size: "));
        add(boardSize);

        String[] pawns = {"0", "-1", "-2"};
        pawnsAmount = new JComboBox<>(pawns);

        add(new JLabel("Select amount of pawns: "));
        add(pawnsAmount);

        Button ok = new Button("OK");
        ok.addActionListener(this::ok);
        add(ok);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void ok(ActionEvent e) {
        setVisible(false);
        new GameWindow(getPlayersAmount(), getBoardSize(), getPawnsAmount());
    }

    public int getPlayersAmount() {
        return Integer.parseInt(Objects.requireNonNull(playersAmount.getSelectedItem()).toString());
    }
    public String getBoardSize() {
        return (String) boardSize.getSelectedItem();
    }
    public int getPawnsAmount() {
        return Integer.parseInt(Objects.requireNonNull(pawnsAmount.getSelectedItem()).toString());
    }
}