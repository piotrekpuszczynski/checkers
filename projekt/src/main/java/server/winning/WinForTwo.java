package server.winning;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

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

    @Override
    public List<Field> getWinningFields(Color color) {
        if (color.equals(Color.GREEN)) return getLower();
        if (color.equals(Color.RED)) return getUpper();
        return null;
    }
}
