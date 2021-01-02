package layout.pawns.states;

/**
 * stan oczekiwania sie dla pionka
 */
public class WaitingState implements PawnStateBehavior {
    /**
     * @return stan oczekiwania
     */
    @Override
    public PawnState getState() {
        return PawnState.WAITING;
    }

    /**
     * @return zmiena stan na stan poruszania sie
     */
    @Override
    public PawnStateBehavior changeState() {
        return PawnState.MOVING.getStateBehavior();
    }
}
