package layout.pawns.states;

import layout.pawns.PawnsPutterFactory;

import java.awt.*;

public class PawnsPutterForThree extends PawnsPutterFactory {
    @Override
    public void putPawns(int pawnsAmount) {
        int[] tab = {11 * (fields.size() - 1) / 12, (11 * (fields.size() - 1) / 12) - 1, 12 * (fields.size() - 1) / 15, (11 * (fields.size() - 1) / 12) - 2, 1, 2, 3};
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(Color.GREEN));
        for (int i = 0; i < pawnsAmount; i++) fields.get(tab[i]).putPawn(putNewPawn(Color.RED));
    }
}
