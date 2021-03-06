package general;

import layout.fields.Field;
import layout.pawns.Pawn;
import server.Client;

import java.util.List;

/**
 * fasada zawierajaca informacje o polach i pionkach stojacyck oraz przesuwanych
 */
public class Facade {
    private List<Field> fields;

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    private GameWindow gameWindow;

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    private BoardPanel boardPanel;

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    private Field lastField;
    private Pawn pawn, lastPawn;

    public void setLastField(Field lastField) {
        this.lastField = lastField;
    }

    public Field getLastField() {
        return lastField;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setLastPawn(Pawn lastPawn) {
        this.lastPawn = lastPawn;
    }

    public Pawn getLastPawn() {
        return lastPawn;
    }

    /**
     * @param field pole
     * @return zwraca indeks na ktorym jest podane pole
     */
    public int getFieldIndex(Field field) {
        for (int i = 0; i < getFields().size(); i++) {
            if (field.equals(getFields().get(i))) return i;
        }
        return 0;
    }

    /**
     * resetuje wszystkie pola na fals dla mozliwosci ruchu
     */
    public void resetAvailability() {
        for (Field field: getFields()) field.setAvailabilityFalse();
    }

    private boolean nextMove = false;

    public void setNextMove(boolean nextMove) {
        this.nextMove = nextMove;
    }

    public boolean getNextMove() {
        return nextMove;
    }

    private List<Field> winningFields;

    public void setWinningFields(List<Field> winningFields) {
        this.winningFields = winningFields;
    }

    public List<Field> getWinningFields() {
        return winningFields;
    }
}
