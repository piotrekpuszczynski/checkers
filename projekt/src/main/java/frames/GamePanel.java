package frames;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GamePanel(int amount) {
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawOval((int) ((getToolkit().getScreenSize().width / 2) - (getToolkit().getScreenSize().height * 0.9 / 2)),
                (int) ((getToolkit().getScreenSize().height / 2) - (getToolkit().getScreenSize().height * 0.96 / 2)),
                (int) (getToolkit().getScreenSize().height * 0.9), (int) (getToolkit().getScreenSize().height * 0.9));
        g.drawLine((getToolkit().getScreenSize().width / 2),
                (int) ((getToolkit().getScreenSize().height / 2) - (getToolkit().getScreenSize().height * 0.96 / 2)),
                (getToolkit().getScreenSize().width / 2), (getToolkit().getScreenSize().height / 2));

    }
}
