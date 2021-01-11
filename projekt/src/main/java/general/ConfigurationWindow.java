package general;

import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * okno do ustawienia parametrow
 */
public class ConfigurationWindow extends JFrame {

    private final JComboBox<String> playersAmount;
    private final JComboBox<String> boardSize;
    private final JComboBox<String> pawnsAmount;
    private String[] pawns;

    /**
     * dodaje zawartosc do okna
     */
    public ConfigurationWindow() {
        super("Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width / 3, getToolkit().getScreenSize().height / 3);
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

        JCheckBox blockingPawn = new JCheckBox(" Change blocking pawn rule");
        blockingPawn.addItemListener(this::getChangingPawnRule);
        add(blockingPawn);

        Button ok = new Button("OK");
        ok.addActionListener(this::ok);
        add(ok);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * @param e zmienia liczby pionkow do wyboru w zaleznosci od rozmiaru planszy
     */
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

    /**
     * @param e potwierdzenie wybranych danych
     */
    public void ok(ActionEvent e) {
        setVisible(false);
        try {
            new Server().runServer(getPlayersAmount(), getBoardSize(), getPawnsAmount(), changingPawnRule);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    boolean changingPawnRule;
    public void getChangingPawnRule(ItemEvent e) {
        changingPawnRule = e.getStateChange() == ItemEvent.SELECTED;
    }
    /**
     * @return zwraca wybtana liczbe graczy
     */
    public String getPlayersAmount() {
        return (String) playersAmount.getSelectedItem();
    }

    /**
     * @return zwraca wybtrana wielkosc planszy
     */
    public String getBoardSize() {
        return (String) boardSize.getSelectedItem();
    }

    /**
     * @return zwraca wybrana liczbe pionkow
     */
    public int getPawnsAmount() {
        return Integer.parseInt(Objects.requireNonNull(pawnsAmount.getSelectedItem()).toString());
    }
}