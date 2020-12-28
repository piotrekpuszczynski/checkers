package layout.pawns;

import java.awt.*;

public class PawnsPutterForFour extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putLeftLower(Color.RED, pawnsAmount);
        putLeftUpper(Color.BLUE, pawnsAmount);
        putRightLower(Color.GREEN, pawnsAmount);
        putRightUpper(Color.YELLOW, pawnsAmount);
    }
}
