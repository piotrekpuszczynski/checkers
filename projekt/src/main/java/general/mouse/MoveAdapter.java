package general.mouse;

import general.Facade;
import layout.fields.Field;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * klasa reprezentujaca myszke
 */
public class MoveAdapter extends MouseAdapter {
    private final Facade facade;

    /**
     * konstruktor
     */
    public MoveAdapter(Facade facade) {
        this.facade = facade;
    }

    /**
     * @param e klikniecie myszki
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (Field field: facade.getFields()) {
            if (field.clicked(e.getX(), e.getY())) {
                if (facade.getNextMove() && field.getAvailability()) {
                    field.putPawn(facade.getLastPawn());
                    facade.getLastField().removePawn();
                    facade.getClient().send("REMOVE " + facade.getFieldIndex(facade.getLastField()) + " " + facade.getLastField().getX() + " " + facade.getLastField().getY() + " " + facade.getLastField().getDiameter() + " " + facade.getNextMove());
                    facade.setLastField(field);
                    facade.getClient().send("PUT " + facade.getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                    facade.resetAvailability();
                } else if (field.getPawn() == null) {
                    if (facade.getPawn() != null && facade.getPawn().getPawnState().getState().equals(facade.getPawn().getMovingState()) && field.getAvailability()) {
                        facade.getPawn().changePawnState();
                        field.putPawn(facade.getPawn());
                        facade.setLastPawn(facade.getPawn());
                        facade.setPawn(null);
                        facade.setLastField(field);
                        facade.resetAvailability();
                        facade.getClient().send("PUT " + facade.getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter());
                    }
                } else if (field.getPawn().getAccess(facade.getClient().getColor()) && facade.getClient().getTurn() && facade.getPawn() == null) {
                    facade.setPawn(field.getPawn());
                    field.removePawn();
                    facade.setLastField(field);
                    facade.getPawn().changePawnState();
                    facade.getClient().send("REMOVE " + facade.getFieldIndex(field) + " " + field.getX() + " " + field.getY() + " " + field.getDiameter() + " " + facade.getNextMove());
                }

                facade.getBoardPanel().repaint();
            }
        }
    }
}
