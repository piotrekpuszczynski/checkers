package layout.pawns.states;

/**
 * interfejs zachowania sie stanu
 */
public interface PawnStateBehavior {
    /**
     * @return swraca stan
     */
    PawnState getState();

    /**
     * @return zwraca zmienionu stan pionka
     */
    PawnStateBehavior changeState();
}
