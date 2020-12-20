package layout.pawns;

import layout.pawns.states.PawnState;
import layout.pawns.states.PawnStateBehavior;

import javax.swing.*;
import java.awt.*;

public class Pawn extends JPanel {

    private final Color color;
    public PawnStateBehavior pawnState;

    Pawn (Color color, PawnState pawnState) {
        this.color = color;
        this.pawnState = pawnState.getStateBehavior();
    }

    public Color getColor() { return this.color; }

    public PawnStateBehavior getPawnState() { return this.pawnState; }
    public void changePawnState() { this.pawnState = pawnState.changeState(); }

}
