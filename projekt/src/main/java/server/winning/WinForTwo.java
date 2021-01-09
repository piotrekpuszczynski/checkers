package server.winning;

import java.awt.*;

/**
 * wygrywanie dla dwoch graczy
 */
public class WinForTwo extends WinFactory {

    @Override
    public Color checkWholeBoard() {
        if (checkUpper(Color.RED) && !winners.contains(Color.RED)) {
            winners.add(Color.RED);
            return Color.RED;
        }
        if (checkLower(Color.GREEN) && !winners.contains(Color.RED)) {
            winners.add(Color.GREEN);
            return Color.GREEN;
        }
        return null;
    }
}
