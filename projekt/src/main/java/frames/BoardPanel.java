package frames;

import frames.mouse.MoveAdapter;
import layout.fields.Field;
import layout.fields.FieldsLayoutFactory;
import layout.fields.LayoutProducer;
import layout.pawns.PawnsPutterFactory;
import layout.pawns.PawnsPutterProducer;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;

    BoardPanel(String playersAmount, String boardSize, int pawnsAmount) {
        fieldsLayout = new LayoutProducer().getFactory(boardSize);
        fieldsLayout.initializeFields(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height - 60);

        PawnsPutterFactory pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
        pawnsPutter.setFields(fieldsLayout.getFields());
        pawnsPutter.putPawns(pawnsAmount);

        addMouseListener(new MoveAdapter(fieldsLayout.getFields(), this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Field field:fieldsLayout.getFields()) {
            field.setGraphics(g);
            field.drawField();
            if (field.getPawn() != null) {
                field.drawPawn();
            }
        }
    }
}
