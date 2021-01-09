package server.winning;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

/**
 * wygrywanie dla czterech graczy
 */
public class WinForFour extends WinFactory {
    @Override
    public Color checkWholeBoard() {
        if (checkRightLower(Color.BLUE) && !winners.contains(Color.BLUE)) {
            winners.add(Color.BLUE);
            return Color.BLUE;
        }
        if (checkLeftLower(Color.GREEN) && !winners.contains(Color.GREEN)) {
            winners.add(Color.GREEN);
            return Color.GREEN;
        }
        if (checkLeftUpper(Color.RED) && !winners.contains(Color.RED)) {
            winners.add(Color.RED);
            return Color.RED;
        }
        if (checkRightUpper(Color.YELLOW) && !winners.contains(Color.YELLOW)) {
            winners.add(Color.YELLOW);
            return Color.YELLOW;
        }
        return null;
    }

    @Override
    public List<Field> getWinningFields(Color color) {
        if (color.equals(Color.GREEN)) return getLeftLower();
        if (color.equals(Color.RED)) return getLeftUpper();
        if (color.equals(Color.BLUE)) return getRightLower();
        if (color.equals(Color.YELLOW)) return getRightUpper();
        return null;
    }
}
