package general;

import general.mouse.MoveAdapter;
import layout.fields.FieldsLayoutFactory;
import layout.fields.LayoutProducer;
import layout.pawns.PawnsPutterFactory;
import layout.pawns.PawnsPutterProducer;

import javax.swing.*;
import java.awt.*;

/**
 * panel planszy
 */
public class BoardPanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;

    /**
     * @param playersAmount liczba graczy
     * @param boardSize rozmiar planszy
     * @param pawnsAmount liczba pionkow
     */
    BoardPanel(String playersAmount, String boardSize, int pawnsAmount, Facade facade) {

        facade.setBoardPanel(this);
        fieldsLayout = new LayoutProducer().getFactory(boardSize);
        fieldsLayout.initializeFields(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height - 100);
        facade.setFields(fieldsLayout.getFields());

        PawnsPutterFactory pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
        pawnsPutter.setFields(facade.getFields());
        pawnsPutter.putPawns(pawnsAmount);

        addMouseListener(new MoveAdapter(facade));
    }

    /**
     * @param g rysuje wszystko
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fieldsLayout.repaintFields(g);
    }
}
