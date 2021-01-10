package server.winning;

import layout.fields.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * fabryka dla wybrania odpowiedniego kryteruim ocenienia czy ktos wygral
 */
public abstract class WinFactory {
    private List<Field> fields;
    protected List<Color> winners = new ArrayList<>();
    private int pawnsAmount;
    int firstX;
    int firstY;
    int diameter;
    int counter, gap, x, y, o;

    /**
     * @param pawnsAmount ustawia ilosc pionkow
     */
    public void setPawnsAmount(int pawnsAmount) { this.pawnsAmount = pawnsAmount; }

    /**
     * ustawia pola
     * @param fields pola
     */
    public void setFields(List<Field> fields) { this.fields = fields; }

    /**
     * metoda sprawdza pole
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @param color kolr ponka
     */
    private boolean get(int x, int y, Color color) {
        for (Field field: fields) {
            if (field.getX() >= x - 1 && field.getX() <= x + 1 && field.getY() == y && field.getPawn() != null
                    && field.getPawn().getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    /**
     * metoda sprawdza pole
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     */
    private Field getField(int x, int y) {
        for (Field field: fields) {
            if (field.getX() >= x - 1 && field.getX() <= x + 1 && field.getY() == y) {
                return field;
            }
        }
        return null;
    }

    /**
     * @param field wyznaczanie danych pierwszego pola do wstawienia do niego pionka
     */
    private void initializeData(Field field) {
        firstX = field.getX();
        firstY = field.getY();
        diameter = field.getDiameter();
        counter = 0;
        gap = 0;
        x = firstX;
        y = firstY;
    }

    /**
     * zmieniannie danych na dane nastepnego pola
     */
    private void incrementData() {
        if (0 == counter) {
            gap++;
            counter = gap;
        } else {
            counter--;
        }
    }

    /**
     * oblicza szerokosc planszy w polach
     */
    private void calculateWidthInFields() {
        o = 0;
        int k = (fields.size() - 1) / 12;
        while (k > 0) {
            o++;
            k -= o;
        }
    }

    /**
     * sprawdza gorny promien
     * @param color kolor pionkow
     */
    public boolean checkUpper(Color color) {
        int amount = 0;
        for (int i = 0; i < pawnsAmount; i++) {
            if (fields.get(i).getPawn() != null && fields.get(i).getPawn().getColor().equals(color)) amount++;
        }
        return amount == pawnsAmount;
    }

    /**
     * sprawdza dolny promien
     * @param color kolor pionkow
     */
    public boolean checkLower(Color color) {
        int amount = 0;
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) {
            if (fields.get(i).getPawn() != null && fields.get(i).getPawn().getColor().equals(color)) amount++;
        }
        return amount == pawnsAmount;
    }

    /**
     * sprawdza prawy dolny promien
     * @param color kolor pionkow
     */
    public boolean checkRightLower(Color color) {
        int amount = 0;

        initializeData(fields.get(11 * (fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            if (get(x, y, color)) amount++;

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
        return amount == pawnsAmount;
    }

    /**
     * sprawdza lewy gorny promien
     * @param color kolor pionkow
     */
    public boolean checkLeftUpper(Color color) {
        int amount = 0;

        initializeData(fields.get((fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            if (get(x, y, color)) amount++;

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
        return amount == pawnsAmount;
    }

    /**
     * sprawdza prawy gorny promien
     * @param color kolor pionkow
     */
    public boolean checkRightUpper(Color color) {
        int amount = 0;

        calculateWidthInFields();

        initializeData(fields.get((fields.size() - 1) / 12 + (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            if (get(x, y, color)) amount++;

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
        return amount == pawnsAmount;
    }

    /**
     * sprawdza lewy dolny promien
     * @param color kolor pionkow
     */
    public boolean checkLeftLower(Color color) {
        int amount = 0;

        calculateWidthInFields();

        initializeData(fields.get((11 * (fields.size() - 1) / 12) - (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            if (get(x, y, color)) amount++;

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
        return amount == pawnsAmount;
    }

    /**
     * @return zwraca czy ktos wygral
     */
    public abstract Color checkWholeBoard();


    /**
     * zwraca gorny promien
     */
    public List<Field> getUpper() {
        List<Field> f = new ArrayList<>();
        for (int i = 0; i < pawnsAmount; i++) {
            f.add(fields.get(i));
        }
        return f;
    }

    /**
     * zwraca dolny promien
     */
    public List<Field> getLower() {
        List<Field> f = new ArrayList<>();
        for (int i = fields.size() - 1; i > fields.size() - pawnsAmount - 1; i--) {
            f.add(fields.get(i));
        }
        return f;
    }

    /**
     * zwraca prawy dolny promien
     */
    public List<Field> getRightLower() {
        List<Field> f = new ArrayList<>();

        initializeData(fields.get(11 * (fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            f.add(getField(x, y));

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
        return f;
    }

    /**
     * zwraca lewy gorny promien
     */
    public List<Field> getLeftUpper() {
        List<Field> f = new ArrayList<>();

        initializeData(fields.get((fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            f.add(getField(x, y));

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
        return f;
    }

    /**
     * zwraca prawy gorny promien
     */
    public List<Field> getRightUpper() {
        List<Field> f = new ArrayList<>();

        calculateWidthInFields();

        initializeData(fields.get((fields.size() - 1) / 12 + (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            f.add(getField(x, y));

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
        return f;
    }

    /**
     * zwraca lewy dolny promien
     */
    public List<Field> getLeftLower() {
        List<Field> f = new ArrayList<>();

        calculateWidthInFields();

        initializeData(fields.get((11 * (fields.size() - 1) / 12) - (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            f.add(getField(x, y));

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
        return f;
    }

    /**
     * @param color kolor pionkow
     * @return liste zwycieskich pol
     */
    public abstract List<Field> getWinningFields(Color color);

    /**
     * @param g rysuje koncowe trojkaty dla poszczegolych kolorow
     */
}
