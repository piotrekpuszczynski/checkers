package layout.fields;

import java.awt.*;

public class FieldsLayoutSmaller1 extends FieldsLayoutFactory {

    @Override
    public void initializeFields(Graphics g, int windowWidth, int windowHeight) {
        diameter = windowHeight / 13;
        start = new double[]{7, 6.5, 6, 2.5, 3, 3.5, 4, 3.5, 3, 2.5, 6, 6.5, 7};
        number = new int[]{1, 2, 3, 10, 9, 8, 7, 8, 9, 10, 3, 2, 1};

        addFields(g, windowWidth, windowHeight);
    }
}
