package layout.fields;

public class LayoutProducer {
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
