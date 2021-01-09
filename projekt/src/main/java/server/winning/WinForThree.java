package server.winning;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

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

    @Override
    public List<Field> getWinningFields(Color color) {
        if (color.equals(Color.GREEN)) return getLeftUpper();
        if (color.equals(Color.RED)) return getRightUpper();
        if (color.equals(Color.BLUE)) return getLower();
        return null;
    }
}
