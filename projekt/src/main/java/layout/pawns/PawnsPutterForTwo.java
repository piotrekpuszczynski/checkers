package layout.pawns;

import java.awt.*;

public class PawnsPutterForTwo extends PawnsPutterFactory {
    @Override
    public void putPawns() {
        for (int i = 0; i < 10; i++) fields.get(i).putPawn(putNewPawn(Color.GREEN));
        for (int i = fields.size() - 1; i > fields.size() - 11; i--) fields.get(i).putPawn(putNewPawn(Color.RED));
    }
}
