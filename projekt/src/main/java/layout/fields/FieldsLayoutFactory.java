package layout.fields;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FieldsLayoutFactory {
    protected final List<Field> fields = new ArrayList<>();
    protected int diameter;
    protected int[] number;
    protected double[] start;

    public abstract void initializeFields(int windowWidth, int windowHeight);


    public void addFields(int windowWidth, int windowHeight) {
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < number[i]; j++) {
                fields.add(new Field((int) (((windowWidth - windowHeight) / 2) + (start[i] * diameter) + (j * diameter)), i * diameter, diameter));
            }
        }
    }

    public List<Field> getFields() { return this.fields; }

    public void repaintFields(Graphics g) {
        for (Field field: fields) {
            field.setGraphics(g);
            if (field.getPawn() != null) {
                field.drawPawn();
            } else if (field.getAvailability()) {
                field.showAvailableField();
            } else field.drawField();
        }
    }
}
