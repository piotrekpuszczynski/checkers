package layout.pawns;

import java.awt.*;

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
}
