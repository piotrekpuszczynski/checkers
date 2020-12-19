package layout;

import java.awt.*;

public class Field {

    private final Graphics g;
    private final int x;
    private final int y;
    private final int diameter;
    Pawn pawn = null;

    public void putPawn(Pawn pawn) {
        this.pawn = pawn;
        g.setColor(pawn.getColor());
        g.fillOval(x, y, diameter, diameter);
    }

    public void removePawn() {
        this.pawn = null;
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }

    public Pawn getPawn() { return pawn; }

    public void drawField() {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }

    Field(Graphics g, int x, int y, int diameter) {
        this.g = g;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        drawField();
    }

}
