package layout.pawns;

import layout.pawns.states.PawnState;
import layout.pawns.states.PawnStateBehavior;

import javax.swing.*;
import java.awt.*;

public class Pawn extends JPanel {

    private final Color color;
    public PawnStateBehavior pawnState;

    Pawn (Color color) {
        this.color = color;
        this.pawnState = PawnState.WAITING.getStateBehavior();
    }

    public Color getColor() { return this.color; }

    public boolean getAccess(Color color) { return color.equals(this.color); }

    public PawnStateBehavior getPawnState() { return this.pawnState; }
    public void changePawnState() { this.pawnState = pawnState.changeState(); }

}
