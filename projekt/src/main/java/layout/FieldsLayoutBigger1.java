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
        start = new double[]{9, 8.5, 8, 7.5, 7, 1.5, 2, 2.5, 3, 3.5, 4, 3.5, 3, 2.5, 2, 1.5, 7, 7.5, 8, 8.5, 9};
        number = new int[]{1, 2, 3, 4, 5, 16, 15, 14, 13, 12, 11, 12, 13, 14, 15, 16, 5, 4, 3, 2, 1};

        addFields(g, windowWidth, windowHeight);
    }
}
