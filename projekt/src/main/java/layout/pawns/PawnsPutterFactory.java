package layout.pawns;

import layout.fields.Field;

import java.awt.*;
import java.util.List;

/**
 * factory dla kladzenia pionkow na planszy
 */
public abstract class PawnsPutterFactory {
    private List<Field> fields;

    int firstX;
    int firstY;
    int diameter;
    int counter, gap, x, y, o;

    /**
     * ustwia dla klasy pola gry
     * @param fields wszystkie pola
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * @param color kolor pionka
     * @return zwraca nowy pionek
     */
    public Pawn putNewPawn(Color color) {
        return new Pawn(color);
    }

    /**
     * kladzie pionki w odpowiednich miejscach i odpowiednim kolorze w zaleznosci od wielkosci planszy i ilosci pionkow
     * @param pawnsAmount liczba pionkow
     */
    public abstract void putPawns(int pawnsAmount);

    /**
     * metoda wstawiajaca pionka w odpowiednim kolorze i w odpowiednie miejsce
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @param color kolr ponka
     */
    private void put(int x, int y, Color color) {
        for (Field field: fields) {
            if (field.getX() >= x - 1 && field.getX() <= x + 1 && field.getY() == y) field.putPawn(putNewPawn(color));
        }
    }

    /**
     * metoda rysujaca docelowe pole
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @param color kolr ponka
     */
    private void draw(int x, int y, Color color) {
        for (Field field: fields) {
            if (field.getX() >= x - 1 && field.getX() <= x + 1 && field.getY() == y) field.drawDestination(color);
        }
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
     * wstawia pionki na gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putUpper(Color color, int pawnsAmount) {
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).putPawn(putNewPawn(color));
    }

    /**
     * wstawia pionki na dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putLower(Color color, int pawnsAmount) {
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) fields.get(i).putPawn(putNewPawn(color));
    }

    /**
     * wstawia pionki na prawy dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putRightLower(Color color, int pawnsAmount) {

        initializeData(fields.get(11 * (fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            put(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    /**
     * wstawia pionki na lewy gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putLeftUpper(Color color, int pawnsAmount) {

        initializeData(fields.get((fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            put(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    /**
     * wstawia pionki na prawy gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putRightUpper(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((fields.size() - 1) / 12 + (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            put(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    /**
     * wstawia pionki na lewy dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void putLeftLower(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((11 * (fields.size() - 1) / 12) - (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            put(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    /**
     * wstawia pionki na gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawUpper(Color color, int pawnsAmount) {
        for (int i = 0; i < pawnsAmount; i++) fields.get(i).drawDestination(color);
    }

    /**
     * wstawia pionki na dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawLower(Color color, int pawnsAmount) {
        for (int i = fields.size() - pawnsAmount; i < fields.size(); i++) fields.get(i).drawDestination(color);
    }

    /**
     * wstawia pionki na prawy dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawRightLower(Color color, int pawnsAmount) {

        initializeData(fields.get(11 * (fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            draw(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    /**
     * wstawia pionki na lewy gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawLeftUpper(Color color, int pawnsAmount) {

        initializeData(fields.get((fields.size() - 1) / 12));

        for (int i = 0; i < pawnsAmount; i++) {

            draw(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    /**
     * wstawia pionki na prawy gorny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawRightUpper(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((fields.size() - 1) / 12 + (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            draw(x, y, color);

            incrementData();

            x = firstX - (2 * gap - counter) * diameter / 2;
            y = firstY + diameter * counter;
        }
    }

    /**
     * wstawia pionki na lewy dolny promien
     * @param color kolor pionkow
     * @param pawnsAmount ilosc pionkow
     */
    public void drawLeftLower(Color color, int pawnsAmount) {

        calculateWidthInFields();

        initializeData(fields.get((11 * (fields.size() - 1) / 12) - (3 * o)));

        for (int i = 0; i < pawnsAmount; i++) {

            draw(x, y, color);

            incrementData();

            x = firstX + (2 * gap - counter) * diameter / 2;
            y = firstY - diameter * counter;
        }
    }

    public abstract void drawDestination(Graphics g, int pawnsAmount);
}
