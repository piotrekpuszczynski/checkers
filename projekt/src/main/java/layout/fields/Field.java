package layout.fields;

import layout.pawns.Pawn;

import java.awt.*;

public class Field {

    private Graphics g;
    private final int x;
    private final int y;
    private final int diameter;
    private Pawn pawn;
    private boolean ifAvailable = false;

    public boolean clicked(int x, int y) {
        return (x - (this.x + diameter / 2)) * (x - (this.x + diameter / 2)) + (y - (this.y + diameter / 2)) * (y - (this.y + diameter / 2)) <= (diameter/2) * (diameter/2);
    }

    public void putPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public void removePawn() {
        this.pawn = null;
    }

    public Pawn getPawn() {
        if (pawn != null) return pawn;
        return null;
    }

    public void drawField() {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }

    public void drawPawn() {
        g.setColor(pawn.getColor());
        g.fillOval(x, y, diameter, diameter);
    }

    public void setAvailabilityTrue() {
        if (this.pawn == null) this.ifAvailable = true;
    }

    public void setAvailabilityFalse() {
        this.ifAvailable = false;
    }

    public boolean getAvailability() { return this.ifAvailable; }

    public void showAvailableField() {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, diameter, diameter);
    }

    public void setGraphics(Graphics g) { this.g = g; }

    Field(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

}
