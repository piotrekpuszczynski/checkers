package layout;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

    Pawn pawn = null;

    public void putPawn(Pawn pawn) { this.pawn = pawn; }

    public void removePawn() { this.pawn = null; }

    public Pawn getPawn() { return pawn; }

    Field(Graphics g, int x, int y, int diameter) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }
}
