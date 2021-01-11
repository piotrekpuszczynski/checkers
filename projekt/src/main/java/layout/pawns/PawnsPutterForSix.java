package layout.pawns;

import java.awt.*;

/**
 * klasa ukladajaca pionki dla szesciu graczy
 */
public class PawnsPutterForSix extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putLeftLower(Color.MAGENTA, pawnsAmount);
        putLeftUpper(Color.CYAN, pawnsAmount);
        putUpper(Color.YELLOW, pawnsAmount);
        putRightUpper(Color.BLUE, pawnsAmount);
        putRightLower(Color.GREEN, pawnsAmount);
        putLower(Color.RED, pawnsAmount);
    }

    @Override
    public void drawDestination(Graphics g, int pawnsAmount) {
        drawRightUpper(Color.MAGENTA, pawnsAmount);
        drawRightLower(Color.CYAN, pawnsAmount);
        drawLower(Color.YELLOW, pawnsAmount);
        drawLeftLower(Color.BLUE, pawnsAmount);
        drawLeftUpper(Color.GREEN, pawnsAmount);
        drawUpper(Color.RED, pawnsAmount);
    }
}
