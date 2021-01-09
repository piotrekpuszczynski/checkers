package server.winning;

import java.awt.*;

public class WinForSix extends WinFactory {
    @Override
    public Color checkWholeBoard() {
        if (checkLeftLower(Color.BLUE)) return Color.BLUE;
        if (checkLeftUpper(Color.GREEN)) return Color.GREEN;
        if (checkUpper(Color.RED)) return Color.RED;
        if (checkLower(Color.YELLOW)) return Color.YELLOW;
        if (checkRightLower(Color.CYAN)) return Color.CYAN;
        if (checkRightUpper(Color.MAGENTA)) return Color.MAGENTA;
        return null;
    }
}
