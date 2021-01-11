package layout.pawns;

import java.awt.*;

/**
 * klasa ukladajaca pionki dla dwoch graczy
 */
public class PawnsPutterForTwo extends PawnsPutterFactory {

    @Override
    public void putPawns(int pawnsAmount) {
        putUpper(Color.GREEN, pawnsAmount);
        putLower(Color.RED, pawnsAmount);
    }

    @Override
    public void drawDestination(Graphics g, int pawnsAmount) {
        drawLower(Color.GREEN, pawnsAmount);
        drawUpper(Color.RED, pawnsAmount);
    }
}
