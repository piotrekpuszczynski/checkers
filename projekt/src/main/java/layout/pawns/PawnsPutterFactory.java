package layout.pawns;

import layout.fields.Field;
import layout.pawns.states.PawnState;

import java.awt.*;
import java.util.List;

public abstract class PawnsPutterFactory {
    protected List<Field> fields;

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Pawn putNewPawn(Color color) {
        return new Pawn(color, PawnState.WAITING);
    }

    public abstract void putPawns();
}
