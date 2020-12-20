package layout.pawns.states;

public interface PawnStateBehavior {
    PawnState getState();
    PawnStateBehavior changeState();
}
