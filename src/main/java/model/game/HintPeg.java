package model.game;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class HintPeg {
    public static final int PEGS_COUNT = Code.PEGS_COUNT;
    private final ObjectProperty<Color> color;
    private HintPegStatus status = HintPegStatus.UNSET;
    private boolean set = false; // czy zostala juz raz ustawiona

    public HintPeg() {
        color = new SimpleObjectProperty<>(status.toColor());
    }

    public ObjectProperty<Color> colorObjectProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void setStatus(HintPegStatus status) {
        this.status = status;
        this.color.setValue(status.toColor());
        set = true;
    }

    public boolean isSet() {
        return set;
    }


}
