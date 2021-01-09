package server.winning;

import java.awt.*;

/**
 * wygrywanie dla czterech graczy
 */
public class WinForFour extends WinFactory {
    @Override
    public Color checkWholeBoard() {
        if (checkRightLower(Color.BLUE)) return Color.BLUE;
        if (checkLeftLower(Color.GREEN)) return Color.GREEN;
        if (checkLeftUpper(Color.RED)) return Color.RED;
        if (checkRightUpper(Color.YELLOW)) return Color.YELLOW;
        return null;
    }
}
