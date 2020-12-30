package layout.pawns;

import java.awt.*;

public class PawnsPutterForThree extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {

        putUpper(Color.BLUE, pawnsAmount);
        putRightLower(Color.GREEN, pawnsAmount);
        putLeftLower(Color.RED, pawnsAmount);
    }
}
