package layout;

public class LayoutProducer {
    public FieldsLayoutFactory getFactory(String choice, int playersAmount, int pawnsAmount) {
        if(choice.equalsIgnoreCase("standard")) {
            return new FieldsLayoutStandard(playersAmount, pawnsAmount);
        } else if(choice.equalsIgnoreCase("+1")) {
            return new FieldsLayoutBigger1(playersAmount, pawnsAmount);
        } else if(choice.equalsIgnoreCase("-1")) {
            return new FieldsLayoutSmaller1(playersAmount, pawnsAmount);
        }
        return null;
    }
}
