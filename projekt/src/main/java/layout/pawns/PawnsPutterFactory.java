package layout.pawns;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

public abstract class PawnsPutterFactory {
    private List<Field> fields;

    int firstX;
    int firstY;
    int diameter;
    int counter, gap, x, y, o;

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Pawn putNewPawn(Color color) {
        return new Pawn(color);
    }

    public abstract void putPawns(int pawnsAmount);

    private void checkFields(int x, int y, Color color) {
        for (Field field: fields) {
            if (field.getX() >= x - 1 && field.getX() <= x + 1 && field.getY() == y) field.putPawn(putNewPawn(color));
        }
    }

    private void initializeData(Field field) {
        firstX = field.getX();
        firstY = field.getY();
        diameter = field.getDiameter();
        counter = 0;
        gap = 0;
        x = firstX;
        y = firstY;
    }

    private void incrementData() {
        if (0 == counter) {
            gap++;
            counter = gap;
        } else {
            counter--;
        }
    }

    private void calculateWidthInFields() {
        o = 0;
        int k = (fields.size() - 1) / 12;
        while (k > 0) {
            o++;
            k -= o;
        }
    }

    public void putUpper(Color color, int pawnsAmount) {
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(color));
    }

    public void putLower(Color color, int pawnsAmount) {
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) fields.get(i).putPawn(putNewPawn(color));
    }

    public void putRightLower(Color color, int pawnsAmount) {

        initializeData(fields.get(11 * (fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            checkFields(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    public void putLeftUpper(Color color, int pawnsAmount) {

        initializeData(fields.get((fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            checkFields(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    public void putRightUpper(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((fields.size() - 1) / 12 + (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            checkFields(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    public void putLeftLower(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((11 * (fields.size() - 1) / 12) - (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            checkFields(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

}
