package layout.pawns;

import java.awt.*;

/**
 * klasa ukladajaca pionki dla trzech graczy
 */
public class PawnsPutterForThree extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putUpper(Color.BLUE, pawnsAmount);
        putRightLower(Color.GREEN, pawnsAmount);
        putLeftLower(Color.RED, pawnsAmount);
    }
}
