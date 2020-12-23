package frames.mouse;

import layout.fields.Field;
import layout.pawns.Pawn;
import layout.pawns.states.PawnState;
import server.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MoveAdapter extends MouseAdapter {
    private final List<Field> fields;
    Pawn pawn;
    private final JPanel panel;
    private final Client client;

    public MoveAdapter(List<Field> fields, JPanel panel, Client client) {
        this.fields = fields;
        this.panel = panel;
        this.client = client;
    }

    private int getFieldIndex(Field field) {
        for (int i = 0; i < fields.size(); i++) {
            if (field.equals(fields.get(i))) return i;
        }
        return 0;
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
                            client.send("PUT " + getFieldIndex(field));
                        }
                    }
                    else System.out.println("null");
                } else if (field.getPawn().getPawnState().getState().equals(PawnState.WAITING)) {
                    System.out.println("clicked " + field.getPawn().getPawnState().getState());
                    pawn = field.getPawn();
                    field.removePawn();
                    pawn.changePawnState();
                    client.send("REMOVE " + getFieldIndex(field));
                }

                panel.repaint();
            }
        }
    }
}
