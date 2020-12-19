package layout;

import javax.swing.*;
import java.awt.*;

public class Pawn extends JPanel {

    private Color color;

    Pawn (Color color) {
        this.color = color;
    }

    public Color getColor() { return this.color; }

}
