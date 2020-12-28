package layout.pawns;

import java.awt.*;

public class PawnsPutterForSix extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putUpper(Color.CYAN, pawnsAmount);
        putLower(Color.MAGENTA, pawnsAmount);
        putLeftLower(Color.RED, pawnsAmount);
        putLeftUpper(Color.BLUE, pawnsAmount);
        putRightLower(Color.GREEN, pawnsAmount);
        putRightUpper(Color.YELLOW, pawnsAmount);
    }
}
