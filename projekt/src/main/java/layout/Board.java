package layout;

import javax.swing.*;
import java.awt.*;

import static java.lang.StrictMath.sqrt;

public class Board extends JPanel {

    public void initializeBoard(Graphics g, int windowWidth, int windowHeight) {
        g.setColor(Color.WHITE);
        g.fillOval((windowWidth / 2) - (windowHeight / 2), 0, windowHeight, windowHeight);

        g.setColor(Color.BLACK);
        int[] xPoints = {windowWidth / 2, (int) ((windowWidth / 2) + (windowHeight * sqrt(3) / 12)),
                (int) ((windowWidth / 2) + (windowHeight * sqrt(3) / 4)), (int) ((windowWidth / 2) + (windowHeight * sqrt(3) / 6)),
                (int) ((windowWidth / 2) + (windowHeight * sqrt(3) / 4)), (int) ((windowWidth / 2) + (windowHeight * sqrt(3) / 12)),
                windowWidth / 2, (int) ((windowWidth / 2) - (windowHeight * sqrt(3) / 12)),
                (int) ((windowWidth / 2) - (windowHeight * sqrt(3) / 4)), (int) ((windowWidth / 2) - (windowHeight * sqrt(3) / 6)),
                (int) ((windowWidth / 2) - (windowHeight * sqrt(3) / 4)), (int) ((windowWidth / 2) - (windowHeight * sqrt(3) / 12))};
        int[] yPoints = {0, windowHeight / 4, windowHeight / 4, windowHeight / 2, 3 * windowHeight / 4, 3 * windowHeight / 4, windowHeight,
                3 * windowHeight / 4, 3 * windowHeight / 4, windowHeight / 2, windowHeight / 4, windowHeight / 4};
        g.drawPolygon(new Polygon(xPoints, yPoints, 12));

    }
}
