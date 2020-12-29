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
    private Pawn pawn;
    public final JPanel panel;
    private final Client client;

    public MoveAdapter(List<Field> fields, JPanel panel, Client client) {
        this.fields = fields;
        this.panel = panel;
        this.client = client;
        this.client.setMouse(this);
    }

    public Field getField(int i) { return fields.get(i); }

    private int getFieldIndex(Field field) {
        for (int i = 0; i < fields.size(); i++) {
            if (field.equals(fields.get(i))) return i;
        }
        return 0;
    }

    private void showAvailableFields(Field field) {
        getField(getFieldIndex(field) - 1).setAvailabilityTrue();
        getField(getFieldIndex(field) + 1).setAvailabilityTrue();
    }

    private void resetAvailability() {
        for (Field field: fields) field.setAvailabilityFalse();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Field field: fields) {
            if (field.clicked(e.getX(), e.getY())) {
                if (field.getPawn() == null) {
                    if (pawn != null) {
                        if (pawn.getPawnState().getState().equals(PawnState.MOVING)) {//&& field.getAvailability()
                            pawn.changePawnState();
                            field.putPawn(pawn);
                            pawn = null;
                            //resetAvailability();
                            client.send("PUT " + getFieldIndex(field));
                        }
                    }
                } else if (field.getPawn().getPawnState().getState().equals(PawnState.WAITING) && field.getPawn().getAccess(client.getColor()) && client.getTurn() && pawn == null) {
                    pawn = field.getPawn();
                    field.removePawn();
                    pawn.changePawnState();
                    //showAvailableFields(field);
                    client.send("REMOVE " + getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                }

                panel.repaint();
            }
        }
    }
}
