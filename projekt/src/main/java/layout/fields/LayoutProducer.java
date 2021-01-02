package layout.fields;

/**
 * klasa zwracajaca odpowiednia klase wielkosci planszy
 */
public class LayoutProducer {
    /**
     * @param choice wybor
     * @return wybrana wielkosc planszy
     */
    public FieldsLayoutFactory getFactory(String choice) {
        if(choice.equalsIgnoreCase("standard")) {
            return new FieldsLayoutStandard();
        } else if(choice.equalsIgnoreCase("bigger")) {
            return new FieldsLayoutBigger();
        } else if(choice.equalsIgnoreCase("smaller")) {
            return new FieldsLayoutSmaller();
        }
        return null;
    }
}
