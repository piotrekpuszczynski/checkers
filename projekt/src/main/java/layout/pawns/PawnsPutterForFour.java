package layout.pawns;

import java.awt.*;

/**
 * klasa ukladajaca pionki dla czterech osob
 */
public class PawnsPutterForFour extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putLeftLower(Color.YELLOW, pawnsAmount);
        putLeftUpper(Color.BLUE, pawnsAmount);
        putRightUpper(Color.GREEN, pawnsAmount);
        putRightLower(Color.RED, pawnsAmount);
    }

    @Override
    public void drawDestination(Graphics g, int pawnsAmount) {
        drawRightUpper(Color.YELLOW, pawnsAmount);
        drawRightLower(Color.BLUE, pawnsAmount);
        drawLeftLower(Color.GREEN, pawnsAmount);
        drawLeftUpper(Color.RED, pawnsAmount);
    }
}
