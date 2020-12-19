package frames;

import layout.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;
    private final PawnsPutterFactory pawnsPutter;

    GamePanel(String playersAmount, String boardSize, int pawnsAmount) {
        fieldsLayout = new LayoutProducer().getFactory(boardSize);

        pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
        pawnsPutter.setFields(fieldsLayout.getFields());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fieldsLayout.initializeFields(g, getWidth(), getHeight());
        pawnsPutter.putPawns();
    }
}
