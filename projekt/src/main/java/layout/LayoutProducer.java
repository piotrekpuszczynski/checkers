package layout;

public class LayoutProducer {
    public FieldsLayoutFactory getFactory(String choice) {
        if(choice.equalsIgnoreCase("standard")) {
            return new FieldsLayoutStandard();
        } else if(choice.equalsIgnoreCase("+1")) {
            return new FieldsLayoutBigger1();
        } else if(choice.equalsIgnoreCase("-1")) {
            return new FieldsLayoutSmaller1();
        }
        return null;
    }
}
