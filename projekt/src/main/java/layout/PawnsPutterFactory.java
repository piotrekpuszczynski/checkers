package layout;

import java.util.List;

public abstract class PawnsPutterFactory {
    protected List<Field> fields;
    protected int playersAmount;
    protected int pawnsAmount;
    protected int diameter;

    PawnsPutterFactory(List<Field> fields, int playersAmount, int pawnsAmount, int diameter) {
        this.fields = fields;
        this.playersAmount = playersAmount;
        this.pawnsAmount = pawnsAmount;
        this.diameter = diameter;
    }

    public abstract void putPawns();
}
