package layout.pawns.states;

public class MovingState implements PawnStateBehavior {
    @Override
    public PawnState getState() {
        return PawnState.MOVING;
    }

    @Override
    public PawnStateBehavior changeState() {
        return PawnState.WAITING.getStateBehavior();
    }
}
