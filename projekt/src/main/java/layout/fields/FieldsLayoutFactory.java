package layout.fields;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * factory do tworzenia planszy o odpowiedniej wiekkosci
 */
public abstract class FieldsLayoutFactory {
    protected final List<Field> fields = new ArrayList<>();
    protected int diameter;
    protected int[] number;
    protected double[] start;

    /**
     * @param windowWidth szerokosc okna
     * @param windowHeight wysokosc okna
     */
    public abstract void initializeFields(int windowWidth, int windowHeight);


    /**
     * dodaje pola w zaleznosci od ustawionych danych
     * @param windowWidth szerokosc okna
     * @param windowHeight wysokosc okna
     */
    public void addFields(int windowWidth, int windowHeight) {
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < number[i]; j++) {
                fields.add(new Field((int) (((windowWidth - windowHeight) / 2) + (start[i] * diameter) + (j * diameter)), i * diameter, diameter));
            }
        }
    }

    /**
     * @return zwraca liste wszystkich pol
     */
    public List<Field> getFields() { return this.fields; }

    /**
     * rysuje pola, pionki i miejsca do mozliwego ruchu
     * @param g grafika
     */
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
