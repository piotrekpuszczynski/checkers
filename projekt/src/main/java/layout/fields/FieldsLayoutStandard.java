package layout.fields;

import java.awt.*;

public class FieldsLayoutStandard extends FieldsLayoutFactory {

    @Override
    public void initializeFields(Graphics g, int windowWidth, int windowHeight) {
        diameter = windowHeight / 17;
        start = new double[]{8, 7.5, 7, 6.5, 2, 2.5, 3, 3.5, 4, 3.5, 3, 2.5, 2, 6.5, 7, 7.5, 8};
        number = new int[]{1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};

        addFields(g, windowWidth, windowHeight);
    }

}
