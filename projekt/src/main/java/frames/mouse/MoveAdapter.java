package frames.mouse;

import frames.BoardPanel;
import layout.fields.Field;
import layout.pawns.Pawn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * klasa reprezentujaca myszke
 */
public class MoveAdapter extends MouseAdapter {
    private final List<Field> fields;
    private Field lastField;
    private Pawn pawn, lastPawn;
    public BoardPanel panel;
    public boolean nextMove = false;

    /**
     * @param fields pola
     * @param panel panel
     */
    public MoveAdapter(List<Field> fields, BoardPanel panel) {
        this.fields = fields;
        this.panel = panel;
    }

    /**
     * @param field ustawia ostatne wybrane pole
     */
    public void setLastField(Field field) { this.lastField = field; }

    /**
     * @return zwraca ostatnie wybrane pole
     */
    public Field getLastField() { return lastField; }

    /**
     * @param pawn ustawia pionek na polu
     */
    public void setPawn(Pawn pawn) { this.pawn = pawn; }

    /**
     * @return zwraca podniesiony pionek
     */
    public Pawn getPawn() { return this.pawn; }

    /**
     * @param i indeks pola z listy
     * @return pole na indeksie i
     */
    public Field getField(int i) { return fields.get(i); }

    /**
     * @param field pole
     * @return zwraca indeks na ktorym jest podane pole
     */
    public int getFieldIndex(Field field) {
        for (int i = 0; i < fields.size(); i++) {
            if (field.equals(fields.get(i))) return i;
        }
        return 0;
    }

    /**
     * resetuje wszystkie pola na fals dla mozliwosci ruchu
     */
    public void resetAvailability() {
        for (Field field: fields) field.setAvailabilityFalse();
    }

    /**
     * @param e klikniecie myszki
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (Field field: fields) {
            if (field.clicked(e.getX(), e.getY())) {
                if (nextMove && field.getAvailability()) {
                    field.putPawn(lastPawn);
                    lastField.removePawn();
                    panel.getGameWindow().getClient().send("REMOVE " + getFieldIndex(lastField) + " " + lastField.getX() + " " + lastField.getY() + " " + lastField.getDiameter() + " " + nextMove);
                    setLastField(field);
                    panel.getGameWindow().getClient().send("PUT " + getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                    resetAvailability();
                } else if (field.getPawn() == null) {
                    if (pawn != null && pawn.getPawnState().getState().equals(pawn.getMovingState()) && field.getAvailability()) {//
                        pawn.changePawnState();
                        field.putPawn(pawn);
                        lastPawn = pawn;
                        pawn = null;
                        setLastField(field);
                        resetAvailability();
                        panel.getGameWindow().getClient().send("PUT " + getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                    }
                } else if (field.getPawn().getAccess(panel.getGameWindow().getClient().getColor()) && panel.getGameWindow().getClient().getTurn() && pawn == null) { //field.getPawn().getPawnState().getState().equals(PawnState.WAITING) &&
                    pawn = field.getPawn();
                    field.removePawn();
                    setLastField(field);
                    pawn.changePawnState();
                    panel.getGameWindow().getClient().send("REMOVE " + getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter() + " " + nextMove);
                }

                panel.repaint();
            }
        }
    }
}
