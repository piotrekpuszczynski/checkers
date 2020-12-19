package layout;

import java.awt.*;
import java.util.ArrayList;

public class FieldsLayoutBigger1 extends FieldsLayoutFactory {
    public FieldsLayoutBigger1(int playersAmount, int pawnsAmount) {
        super(playersAmount, pawnsAmount);
    }

    @Override
    public void initializeFields(Graphics g, int windowWidth, int windowHeight) {
        diameter = windowHeight / 21;
        fields = new ArrayList<>();
        double[] start = {8, 7.5, 7, 6.5, 6, 1.5, 2, 2.5, 3, 3.5, 4, 3.5, 3, 2.5, 2, 1.5, 6, 6.5, 7, 7.5, 8};
        int[] number = {1, 2, 3, 4, 5, 14, 13, 12, 11, 10, 9, 10, 11, 12, 13, 14, 5, 4, 3, 2, 1};
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < number[i]; j++) {
                fields.add(new Field(g, (int) (((windowWidth - windowHeight) / 2) + (start[i] * diameter) + (j * diameter)), i * diameter, diameter));
            }
        }
    }
}
