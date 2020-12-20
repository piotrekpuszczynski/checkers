package layout.pawns.states;

public enum PawnState {

    WAITING {
        public PawnStateBehavior getStateBehavior() {
            return new WaitingState();
        }
    },
    MOVING {
        public PawnStateBehavior getStateBehavior() {
            return new MovingState();
        }
    };

    public PawnStateBehavior getStateBehavior() { return null; }
}
