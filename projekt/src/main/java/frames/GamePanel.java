package frames;

import layout.FieldsLayoutFactory;
import layout.LayoutProducer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;

    GamePanel(int playersAmount, String boardSize, int pawnsAmount) {
        fieldsLayout = new LayoutProducer().getFactory(boardSize, playersAmount, pawnsAmount);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fieldsLayout.initializeFields(g, getWidth(), getHeight());
    }
}
