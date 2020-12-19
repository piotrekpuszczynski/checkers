package layout;

import java.util.List;

public abstract class PawnsPutterFactory {
    protected List<Field> fields;

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public abstract void putPawns();
}
