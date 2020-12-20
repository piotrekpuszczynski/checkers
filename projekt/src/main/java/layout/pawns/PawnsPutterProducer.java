package layout.pawns;

import layout.fields.Field;

import java.util.List;

public class PawnsPutterProducer {

    public PawnsPutterFactory getPutter(String choice) {
        if(choice.equalsIgnoreCase("2")) {
            return new PawnsPutterForTwo();
        //} else if(choice.equalsIgnoreCase("+1")) {
        //    return new FieldsLayoutBigger1(playersAmount, pawnsAmount);
        //} else if(choice.equalsIgnoreCase("-1")) {
        //    return new FieldsLayoutSmaller1(playersAmount, pawnsAmount);
        }
        return null;
    }
}
