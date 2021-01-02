package layout.pawns;

import layout.pawns.states.PawnState;
import layout.pawns.states.PawnStateBehavior;

import javax.swing.*;
import java.awt.*;

/**
 * klasa pionka
 */
public class Pawn extends JPanel {

    private final Color color;
    public PawnStateBehavior pawnState;

    /**
     * @param color kolor pionka
     */
    Pawn (Color color) {
        this.color = color;
        this.pawnState = PawnState.WAITING.getStateBehavior();
    }

    /**
     * @return zeraca kolor pionka
     */
    public Color getColor() { return this.color; }

    /**
     * @param color kolor pionka
     * @return zwraca czy kracz ma dostep do pionka
     */
    public boolean getAccess(Color color) { return color.equals(this.color); }

    /**
     * @return zwraca aktualny stan pionka
     */
    public PawnStateBehavior getPawnState() { return this.pawnState; }

    /**
     * zmienia stan pionka
     */
    public void changePawnState() { this.pawnState = pawnState.changeState(); }

    /**
     * @return zwraca stan poruszajacego sie pionka
     */
    public PawnState getMovingState() { return PawnState.MOVING.getStateBehavior().getState(); }
}
