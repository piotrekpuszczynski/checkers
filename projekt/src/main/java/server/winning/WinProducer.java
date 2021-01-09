package server.winning;

/**
 * klasa tworzaca odpowiednie rozszezenie factory do stawiania pionkow
 */
public class WinProducer {

    /**
     * @param choice wybor
     * @return klasa stawiajaca odpowiednia klase rozszezajaca
     */
    public WinFactory getWin(String choice) {
        if(choice.equalsIgnoreCase("2")) {
            return new WinForTwo();
        } else if(choice.equalsIgnoreCase("3")) {
            return new WinForThree();
        } else if(choice.equalsIgnoreCase("4")) {
            return new WinForFour();
        } else if(choice.equalsIgnoreCase("6")) {
            return new WinForSix();
        }
        return null;
    }
}
