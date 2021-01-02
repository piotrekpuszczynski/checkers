package layout.pawns;

/**
 * klasa tworzaca odpowiednie rozszezenie factory do stawiania pionkow
 */
public class PawnsPutterProducer {

    /**
     * @param choice wybor
     * @return klasa stawiajaca odpowiednia ilosc pionkow
     */
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
