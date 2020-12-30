package layout.pawns;

import java.awt.*;

public class PawnsPutterForFour extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putLeftLower(Color.YELLOW, pawnsAmount);
        putLeftUpper(Color.BLUE, pawnsAmount);
        putRightUpper(Color.GREEN, pawnsAmount);
        putRightLower(Color.RED, pawnsAmount);
    }
}
