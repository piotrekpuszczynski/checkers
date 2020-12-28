package layout.pawns;

import layout.fields.Field;

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

    public abstract void putPawns(int pawnsAmount);

    public void putUpper(Color color, int pawnsAmount) {
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(color));
    }

    public void putLower(Color color, int pawnsAmount) {
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) fields.get(i).putPawn(putNewPawn(color));
    }

    public void putRightLower(Color color, int pawnsAmount) {
        int firstX = fields.get(11 * (fields.size() - 1) / 12).getX();
        int firstY = fields.get(11 * (fields.size() - 1) / 12).getY();
        int diameter = fields.get(11 * (fields.size() - 1) / 12).getDiameter();
        int counter = 0, gap = 0, x = firstX, y = firstY;
        for (int i = 0; i < pawnsAmount; i++) {

            for (Field field: fields) {
                if (field.getX() >= x - 2 && field.getX() <= x + 2 && field.getY() == y) field.putPawn(putNewPawn(color));
            }

            if (0 == counter) {
                gap++;
                counter = gap;
            } else {
                counter--;
            }

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    public void putLeftUpper(Color color, int pawnsAmount) {
        int firstX = fields.get((fields.size() - 1) / 12).getX();
        int firstY = fields.get((fields.size() - 1) / 12).getY();
        int diameter = fields.get((fields.size() - 1) / 12).getDiameter();
        int counter = 0, gap = 0, x = firstX, y = firstY;
        for (int i = 0; i < pawnsAmount; i++) {

            for (Field field: fields) {
                if (field.getX() >= x - 2 && field.getX() <= x + 2 && field.getY() == y) field.putPawn(putNewPawn(color));
            }

            if (0 == counter) {
                gap++;
                counter = gap;
            } else {
                counter--;
            }

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    public void putRightUpper(Color color, int pawnsAmount) {

        int o = 0;
        int k = (fields.size() - 1) / 12;
        while(k > 0) {
            o++;
            k -= o;
        }

        int firstX = fields.get((fields.size() - 1) / 12 + (3 * o)).getX();
        int firstY = fields.get((fields.size() - 1) / 12 + (3 * o)).getY();
        int diameter = fields.get((fields.size() - 1) / 12 + (3 * o)).getDiameter();
        int counter = 0, gap = 0, x = firstX, y = firstY;

        for (int i = 0; i < pawnsAmount; i++) {

            for (Field field: fields) {
                if (field.getX() >= x - 2 && field.getX() <= x + 2 && field.getY() == y) field.putPawn(putNewPawn(color));
            }

            if (0 == counter) {
                gap++;
                counter = gap;
            } else {
                counter--;
            }

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    public void putLeftLower(Color color, int pawnsAmount) {

        int o = 0;
        int k = (fields.size() - 1) / 12;
        while(k > 0) {
            o++;
            k -= o;
        }

        int firstX = fields.get((11 * (fields.size() - 1) / 12) - (3 * o)).getX();
        int firstY = fields.get((11 * (fields.size() - 1) / 12) - (3 * o)).getY();
        int diameter = fields.get((11 * (fields.size() - 1) / 12) - (3 * o)).getDiameter();
        int counter = 0, gap = 0, x = firstX, y = firstY;

        for (int i = 0; i < pawnsAmount; i++) {

            for (Field field: fields) {
                if (field.getX() >= x - 2 && field.getX() <= x + 2 && field.getY() == y) field.putPawn(putNewPawn(color));
            }

            if (0 == counter) {
                gap++;
                counter = gap;
            } else {
                counter--;
            }

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

}
