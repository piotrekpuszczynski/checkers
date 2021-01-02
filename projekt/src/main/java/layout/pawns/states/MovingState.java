package layout.pawns.states;

/**
 * stan poruszania sie dla pionka
 */
public class MovingState implements PawnStateBehavior {
    /**
     * @return stan poruszania sie
     */
    @Override
    public PawnState getState() {
        return PawnState.MOVING;
    }

    /**
     * @return zmiena stan na stan oczekiwania
     */
    @Override
    public PawnStateBehavior changeState() {
        return PawnState.WAITING.getStateBehavior();
    }
}
