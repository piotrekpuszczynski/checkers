package layout.pawns;

import java.awt.*;

public class PawnsPutterForTwo extends PawnsPutterFactory {

    @Override
    public void putPawns(int pawnsAmount) {
        putUpper(Color.GREEN, pawnsAmount);
        putLower(Color.RED, pawnsAmount);
    }
}
