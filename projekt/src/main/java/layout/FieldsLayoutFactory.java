package layout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class FieldsLayoutFactory extends JPanel {
    List<Field> fields;
    int playersAmount;
    String boardSize;
    int pawnsAmount;

    public abstract void initializeFields(Graphics g);

    FieldsLayoutFactory(int playersAmount, String boardSize, int pawnsAmount) {
        this.playersAmount = playersAmount;
        this.boardSize = boardSize;
        this.pawnsAmount = pawnsAmount;
    }
}
