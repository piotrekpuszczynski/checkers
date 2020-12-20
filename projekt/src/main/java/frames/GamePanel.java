package frames;

import frames.mouse.MoveAdapter;
import layout.fields.FieldsLayoutFactory;
import layout.fields.LayoutProducer;
import layout.pawns.PawnsPutterFactory;
import layout.pawns.PawnsPutterProducer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;
    private final PawnsPutterFactory pawnsPutter;

    GamePanel(String playersAmount, String boardSize, int pawnsAmount) {
        fieldsLayout = new LayoutProducer().getFactory(boardSize);

        pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
        pawnsPutter.setFields(fieldsLayout.getFields());

        addMouseListener(new MoveAdapter(fieldsLayout.getFields(), this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fieldsLayout.initializeFields(g, getWidth(), getHeight());
        pawnsPutter.putPawns();
    }
}
