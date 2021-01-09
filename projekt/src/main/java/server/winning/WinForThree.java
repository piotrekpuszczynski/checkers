package server.winning;

import java.awt.*;

/**
 * wygrywanie dla trzech graczy
 */
public class WinForThree extends WinFactory {
    @Override
    public Color checkWholeBoard() {
        if (checkLower(Color.BLUE) && !winners.contains(Color.BLUE)) {
            winners.add(Color.BLUE);
            return Color.BLUE;
        }
        if (checkLeftUpper(Color.GREEN) && !winners.contains(Color.GREEN)) {
            winners.add(Color.GREEN);
            return Color.GREEN;
        }
        if (checkRightUpper(Color.RED) && !winners.contains(Color.RED)) {
            winners.add(Color.RED);
            return Color.RED;
        }
        return null;
    }
}
