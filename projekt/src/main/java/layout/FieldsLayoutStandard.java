package layout;

import java.awt.*;
import java.util.ArrayList;

public class FieldsLayoutStandard extends FieldsLayoutFactory {
    public FieldsLayoutStandard(int playersAmount, String boardSize, int pawnsAmount) {
        super(playersAmount, boardSize, pawnsAmount);
    }

    @Override
    public void initializeFields(Graphics g) {
        fields = new ArrayList<>();
        fields.add(new Field(g, 30, 30, 100));
    }
}
