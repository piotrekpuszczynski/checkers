package frames;

import layout.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Board board;
    GamePanel(int amount) {
        setBackground(Color.BLACK);
        board = new Board();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.initializeBoard(g, getWidth(), getHeight());
    }
}
