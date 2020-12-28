package layout.pawns;

import java.awt.*;

public class PawnsPutterForThree extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putUpper(Color.GREEN, pawnsAmount);
        putRightLower(Color.RED, pawnsAmount);
        putLeftLower(Color.BLUE, pawnsAmount);
    }
}
