package layout.pawns;

public class PawnsPutterProducer {

    //TODO for more players than two
    public PawnsPutterFactory getPutter(String choice) {
        if(choice.equalsIgnoreCase("2")) {
            return new PawnsPutterForTwo();
        } else if(choice.equalsIgnoreCase("3")) {
            return new PawnsPutterForThree();
        }
        return null;
    }
}
