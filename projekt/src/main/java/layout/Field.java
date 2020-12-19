package layout;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

    Field(Graphics g, int x, int y, int radius) {
        g.setColor(Color.GREEN);
        g.drawOval(x, y, radius, radius);
    }
}
