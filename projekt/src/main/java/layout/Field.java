package layout;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

    Field(Graphics g, int x, int y, int diameter) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }
}
