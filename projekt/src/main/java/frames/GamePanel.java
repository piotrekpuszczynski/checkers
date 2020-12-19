package frames;

import layout.Board;
import layout.FieldsLayoutStandard;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Board board;
    GamePanel(int playersAmount, String boardSize, int pawnsAmount) {
        setBackground(Color.BLACK);
        board = new Board();
        board.setFieldsLayout(new FieldsLayoutStandard(playersAmount, boardSize, pawnsAmount));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.initializeBoard(g, getWidth(), getHeight());
        board.getFieldsLayout().initializeFields(g);
    }
}
