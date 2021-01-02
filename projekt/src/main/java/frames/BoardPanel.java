package frames;

import frames.mouse.MoveAdapter;
import layout.fields.Field;
import layout.fields.FieldsLayoutFactory;
import layout.fields.LayoutProducer;
import layout.pawns.PawnsPutterFactory;
import layout.pawns.PawnsPutterProducer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * panel planszy
 */
public class BoardPanel extends JPanel {
    private final FieldsLayoutFactory fieldsLayout;
    private final GameWindow gameWindow;
    private final MoveAdapter mouse;

    /**
     * @return zwraca okno w ktorym jest panel
     */
    public GameWindow getGameWindow() { return this.gameWindow; }

    /**
     * @return zwraca myszke ktora obsluguje plansze
     */
    public MoveAdapter getMouse() { return this.mouse; }

    /**
     * @return zwraca wszystkie pola
     */
    public List<Field> getAllFields() { return this.fieldsLayout.getFields(); }

    /**
     * @param playersAmount liczba graczy
     * @param boardSize rozmiar planszy
     * @param pawnsAmount liczba pionkow
     * @param gameWindow okno
     */
    BoardPanel(String playersAmount, String boardSize, int pawnsAmount, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        fieldsLayout = new LayoutProducer().getFactory(boardSize);
        fieldsLayout.initializeFields(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height - 100);

        PawnsPutterFactory pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
        pawnsPutter.setFields(fieldsLayout.getFields());
        pawnsPutter.putPawns(pawnsAmount);

        mouse = new MoveAdapter(fieldsLayout.getFields(), this);
        addMouseListener(mouse);
    }

    /**
     * @param g rysuje wszystko
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fieldsLayout.repaintFields(g);
    }
}
