package frames.mouse;

import frames.BoardPanel;
import layout.fields.Field;
import layout.pawns.Pawn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MoveAdapter extends MouseAdapter {
    private final List<Field> fields;
    private Field lastField;
    private Pawn pawn;
    public BoardPanel panel;

    public MoveAdapter(List<Field> fields, BoardPanel panel) {
        this.fields = fields;
        this.panel = panel;
    }

    public void setLastField(Field field) { this.lastField = field; }

    public Field getLastField() { return lastField; }

    public void setPawn(Pawn pawn) { this.pawn = pawn; }
    public Pawn getPawn() { return this.pawn; }

    public Field getField(int i) { return fields.get(i); }

    public int getFieldIndex(Field field) {
        for (int i = 0; i < fields.size(); i++) {
            if (field.equals(fields.get(i))) return i;
        }
        return 0;
    }

    public void resetAvailability() {
        for (Field field: fields) field.setAvailabilityFalse();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Field field: fields) {
            if (field.clicked(e.getX(), e.getY())) {
                if (field.getPawn() == null) {
                    if (pawn != null && pawn.getPawnState().getState().equals(pawn.getMovingState()) && field.getAvailability()) {
                        pawn.changePawnState();
                        field.putPawn(pawn);
                        pawn = null;
                        setLastField(null);
                        resetAvailability();
                        panel.getGameWindow().getClient().send("PUT " + getFieldIndex(field));
                    }
                } else if (field.getPawn().getAccess(panel.getGameWindow().getClient().getColor()) && panel.getGameWindow().getClient().getTurn() && pawn == null) { //field.getPawn().getPawnState().getState().equals(PawnState.WAITING) &&
                    pawn = field.getPawn();
                    field.removePawn();
                    setLastField(field);
                    pawn.changePawnState();
                    panel.getGameWindow().getClient().send("REMOVE " + getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                }

                panel.repaint();
            }
        }
    }
}
