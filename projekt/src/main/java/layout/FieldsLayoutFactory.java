package layout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class FieldsLayoutFactory extends JPanel {
    protected List<Field> fields;
    protected int playersAmount;
    protected int pawnsAmount;
    protected int diameter;

    public abstract void initializeFields(Graphics g, int windowWidth, int windowHeight);

    FieldsLayoutFactory(int playersAmount, int pawnsAmount) {
        this.playersAmount = playersAmount;
        this.pawnsAmount = pawnsAmount;
    }
}
