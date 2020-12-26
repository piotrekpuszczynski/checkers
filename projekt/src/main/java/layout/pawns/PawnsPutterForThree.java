package layout.pawns;

import java.awt.*;

public class PawnsPutterForThree extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {
        int[] tab = {11 * (fields.size() - 1) / 12,
                (11 * (fields.size() - 1) / 12) - 1, 22 * (fields.size() - 1) / 27,
                (11 * (fields.size() - 1) / 12) - 2, 11, 12, 13};
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(Color.GREEN));
        for (int i = 0; i < pawnsAmount; i++) fields.get(tab[i]).putPawn(putNewPawn(Color.RED));
    }
}
