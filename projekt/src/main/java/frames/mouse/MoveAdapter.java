package frames.mouse;

import layout.fields.Field;
import layout.pawns.Pawn;
import layout.pawns.states.PawnState;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MoveAdapter extends MouseAdapter {
    List<Field> fields;
    Pawn pawn;
    JPanel panel;

    public MoveAdapter(List<Field> fields, JPanel panel) {
        this.fields = fields;
        this.panel = panel;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        for (Field field: fields) {
            if (field.clicked(e.getX(), e.getY())) {
                if (field.getPawn() == null) {
                    if (pawn != null) {
                        if (pawn.getPawnState().getState().equals(PawnState.MOVING)) {
                            System.out.println("clicked " + pawn.getPawnState().getState());
                            pawn.changePawnState();
                            field.putPawn(pawn);
                            pawn = null;
                        }
                    }
                    else System.out.println("null");
                } else if (field.getPawn().getPawnState().getState().equals(PawnState.WAITING)) {
                    System.out.println("clicked " + field.getPawn().getPawnState().getState());
                    pawn = field.getPawn();
                    field.removePawn();
                    pawn.changePawnState();

                }

                panel.repaint();
            }
        }
    }
}
