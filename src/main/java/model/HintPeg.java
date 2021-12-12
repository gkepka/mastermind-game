package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class HintPeg {
    private final ObjectProperty<Color> color;
    private boolean set = false; // czy zostala juz raz ustawiona

    public HintPeg() {
        color = new SimpleObjectProperty<>(new Color(255, 255, 255, 0));
    }

    public ObjectProperty<Color> colorObjectProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void setColor(Color color) {
        this.color.setValue(color);
    }

    public boolean isSet() {
        return set;
    }


}
