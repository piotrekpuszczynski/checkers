package layout.pawns;

import layout.pawns.states.PawnsPutterForThree;

public class PawnsPutterProducer {

    public PawnsPutterFactory getPutter(String choice) {
        if(choice.equalsIgnoreCase("2")) {
            return new PawnsPutterForTwo();
        } else if(choice.equalsIgnoreCase("3")) {
            return new PawnsPutterForThree();
        //} else if(choice.equalsIgnoreCase("-1")) {
        //    return new FieldsLayoutSmaller1(playersAmount, pawnsAmount);
        }
        return null;
    }
}
