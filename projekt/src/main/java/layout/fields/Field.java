package layout.fields;

import layout.pawns.Pawn;

import java.awt.*;

/**
 * klasa pola
 */
public class Field {

    private Graphics g;
    private final int x;
    private final int y;
    private final int diameter;
    private Pawn pawn;
    private boolean ifAvailable = false;

    /**
     * @return zwraca wspolrzena x pola
     */
    public int getX() { return this.x; }

    /**
     * @return zwraca wspolrzedna y pola
     */
    public int getY() { return this.y; }

    /**
     * @return zwraca srednice pola
     */
    public int getDiameter() { return this.diameter; }

    /**
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @return zwraca czy pole zostalo klikniete
     */
    public boolean clicked(int x, int y) {
        return (x - (this.x + diameter / 2)) * (x - (this.x + diameter / 2)) + (y - (this.y + diameter / 2)) * (y - (this.y + diameter / 2)) <= (diameter/2) * (diameter/2);
    }

    /**
     * @param pawn stawia pionka na polu
     */
    public void putPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    /**
     * usuwa pionka z pola
     */
    public void removePawn() {
        this.pawn = null;
    }

    /**
     * @return zwraca pionka ktory jest na polu
     */
    public Pawn getPawn() {
        if (pawn != null) return pawn;
        return null;
    }

    /**
     * rysuje pole
     */
    public void drawField() {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }

    /**
     * rysuje pionka
     */
    public void drawPawn() {
        g.setColor(pawn.getColor());
        g.fillOval(x, y, diameter, diameter);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, diameter, diameter);
    }

    /**
     * ustawia ze mozna ruszyc sie na pole
     */
    public void setAvailabilityTrue() {
        if (this.pawn == null) this.ifAvailable = true;
    }

    /**
     * ustawia ze nie mozna sie ruszyc na pole
     */
    public void setAvailabilityFalse() {
        this.ifAvailable = false;
    }

    /**
     * @return zwraca czy mozna sie ruszyc na pole
     */
    public boolean getAvailability() { return this.ifAvailable; }

    /**
     * rysuje ze mozna ruszyc sie na pole
     */
    public void showAvailableField() {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, diameter, diameter);
    }

    /**
     * @param g ustawia grafike
     */
    public void setGraphics(Graphics g) { this.g = g; }

    /**
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @param diameter srednica
     */
    Field(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public void drawDestination(Color color) {
        g.setColor(color);
        g.drawOval(x, y, diameter, diameter);
    }

}
