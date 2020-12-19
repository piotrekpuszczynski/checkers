package layout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FieldsLayoutFactory {
    protected List<Field> fields = new ArrayList<>();
    protected int playersAmount;
    protected int pawnsAmount;
    protected int diameter;
    protected int[] number;
    protected double[] start;

    public abstract void initializeFields(Graphics g, int windowWidth, int windowHeight);

    FieldsLayoutFactory(int playersAmount, int pawnsAmount) {
        this.playersAmount = playersAmount;
        this.pawnsAmount = pawnsAmount;
    }

    public void addFields(Graphics g, int windowWidth, int windowHeight) {
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < number[i]; j++) {
                fields.add(new Field(g, (int) (((windowWidth - windowHeight) / 2) + (start[i] * diameter) + (j * diameter)), i * diameter, diameter));
            }
        }
    }
}
