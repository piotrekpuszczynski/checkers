package frames.mouse;

import layout.fields.Field;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MoveAdapter extends MouseAdapter {
    List<Field> fields;

    public MoveAdapter(List<Field> fields) {
        this.fields = fields;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        for (Field field: fields) if (field.clicked(e.getX(), e.getY())) System.out.println("clicked" + field.toString());
    }
}
