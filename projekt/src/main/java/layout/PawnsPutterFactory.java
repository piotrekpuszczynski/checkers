package layout;

import java.awt.*;
import java.util.List;

public abstract class PawnsPutterFactory {
    protected List<Field> fields;

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Pawn putNewPawn(Color color) {
        return new Pawn(color);
    }

    public abstract void putPawns();
}
