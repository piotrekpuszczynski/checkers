package layout.fields;

/**
 * klasa reprezentujaca wieksza plansze
 */
public class FieldsLayoutBigger extends FieldsLayoutFactory {

    @Override
    public void initializeFields(int windowWidth, int windowHeight) {
        diameter = windowHeight / 21;
        start = new double[]{9, 8.5, 8, 7.5, 7, 1.5, 2, 2.5, 3, 3.5, 4, 3.5, 3, 2.5, 2, 1.5, 7, 7.5, 8, 8.5, 9};
        number = new int[]{1, 2, 3, 4, 5, 16, 15, 14, 13, 12, 11, 12, 13, 14, 15, 16, 5, 4, 3, 2, 1};

        addFields(windowWidth, windowHeight);
    }
}
