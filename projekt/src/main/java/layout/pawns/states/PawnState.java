package layout.pawns.states;

/**
 * enum stanow
 */
public enum PawnState {

    /**
     * stan oczekowania
     */
    WAITING {
        public PawnStateBehavior getStateBehavior() {
            return new WaitingState();
        }
    },
    /**
     * stan poruszania sie
     */
    MOVING {
        public PawnStateBehavior getStateBehavior() {
            return new MovingState();
        }
    };

    /**
     * @return zwraca zachowanie sie stanu
     */
    public PawnStateBehavior getStateBehavior() { return null; }
}
