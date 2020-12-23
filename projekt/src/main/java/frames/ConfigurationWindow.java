package frames;

import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class ConfigurationWindow extends JFrame {

    private final JComboBox<String> playersAmount;
    private final JComboBox<String> boardSize;
    private final JComboBox<String> pawnsAmount;
    private String[] pawns;

    public ConfigurationWindow() {
        super("Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width/3, getToolkit().getScreenSize().height/3);
        setLayout(new GridLayout(4, 2));

        String[] amount = {"2", "3", "4", "6"};
        playersAmount = new JComboBox<>(amount);

        add(new JLabel("Select amount of players: "));
        add(playersAmount);

        String[] sizes = {"smaller", "standard", "bigger"};
        boardSize = new JComboBox<>(sizes);
        boardSize.addActionListener((this::changePawnsChoice));

        add(new JLabel("Select board size: "));
        add(boardSize);

        pawns = new String[]{"6", "3", "1"};
        pawnsAmount = new JComboBox<>(pawns);

        add(new JLabel("Select amount of pawns: "));
        add(pawnsAmount);

        Button ok = new Button("OK");
        ok.addActionListener(this::ok);
        add(ok);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changePawnsChoice(ActionEvent e) {
        switch ((String) Objects.requireNonNull(boardSize.getSelectedItem())) {
            case "smaller":
                pawns = new String[]{"6", "3", "1"};
                break;
            case "standard":
                pawns = new String[]{"10", "6", "3", "1"};
                break;
            case "bigger":
                pawns = new String[]{"15", "10", "6", "3", "1"};
                break;
        }
        pawnsAmount.setModel(new DefaultComboBoxModel<>(pawns));
    }

    public void ok(ActionEvent e) {
        setVisible(false);
        //new GameWindow(getPlayersAmount(), getBoardSize(), getPawnsAmount());
        try {
            new Server().runServer(getPlayersAmount(), getBoardSize(), getPawnsAmount());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getPlayersAmount() {
        return (String) playersAmount.getSelectedItem();
    }
    public String getBoardSize() {
        return (String) boardSize.getSelectedItem();
    }
    public int getPawnsAmount() {
        return Integer.parseInt(Objects.requireNonNull(pawnsAmount.getSelectedItem()).toString());
    }
}