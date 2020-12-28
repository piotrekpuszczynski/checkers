package layout.pawns;

public class PawnsPutterProducer {

    public PawnsPutterFactory getPutter(String choice) {
        if(choice.equalsIgnoreCase("2")) {
            return new PawnsPutterForTwo();
        } else if(choice.equalsIgnoreCase("3")) {
            return new PawnsPutterForThree();
        } else if(choice.equalsIgnoreCase("4")) {
            return new PawnsPutterForFour();
        } else if(choice.equalsIgnoreCase("6")) {
            return new PawnsPutterForSix();
        }
        return null;
    }
}
