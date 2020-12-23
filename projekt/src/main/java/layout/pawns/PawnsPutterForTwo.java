package layout.pawns;

import java.awt.*;

public class PawnsPutterForTwo extends PawnsPutterFactory {

    @Override
    public void putPawns(int pawnsAmount) {
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(Color.GREEN));
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) fields.get(i).putPawn(putNewPawn(Color.RED));
    }
}
