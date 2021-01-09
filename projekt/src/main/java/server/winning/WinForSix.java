package server.winning;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

/**
 * wygrywanie dla szesciu graczy
 */
public class WinForSix extends WinFactory {
    @Override
    public Color checkWholeBoard() {
        if (checkLeftLower(Color.BLUE) && !winners.contains(Color.BLUE)) {
            winners.add(Color.BLUE);
            return Color.BLUE;
        }
        if (checkLeftUpper(Color.GREEN) && !winners.contains(Color.GREEN)) {
            winners.add(Color.GREEN);
            return Color.GREEN;
        }
        if (checkUpper(Color.RED) && !winners.contains(Color.RED)) {
            winners.add(Color.RED);
            return Color.RED;
        }
        if (checkLower(Color.YELLOW) && !winners.contains(Color.YELLOW)) {
            winners.add(Color.YELLOW);
            return Color.YELLOW;
        }
        if (checkRightLower(Color.CYAN) && !winners.contains(Color.CYAN)) {
            winners.add(Color.CYAN);
            return Color.CYAN;
        }
        if (checkRightUpper(Color.MAGENTA) && !winners.contains(Color.MAGENTA)) {
            winners.add(Color.MAGENTA);
            return Color.MAGENTA;
        }
        return null;
    }

    @Override
    public List<Field> getWinningFields(Color color) {
        if (color.equals(Color.GREEN)) return getLeftUpper();
        if (color.equals(Color.RED)) return getUpper();
        if (color.equals(Color.BLUE)) return getLeftLower();
        if (color.equals(Color.YELLOW)) return getLower();
        if (color.equals(Color.CYAN)) return getRightLower();
        if (color.equals(Color.MAGENTA)) return getRightUpper();
        return null;
    }
}
