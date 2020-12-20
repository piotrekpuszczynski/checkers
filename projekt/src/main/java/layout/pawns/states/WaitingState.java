package layout.pawns.states;

public class WaitingState implements PawnStateBehavior {
    @Override
    public PawnState getState() {
        return PawnState.WAITING;
    }

    @Override
    public PawnStateBehavior changeState() {
        return PawnState.MOVING.getStateBehavior();
    }
}
