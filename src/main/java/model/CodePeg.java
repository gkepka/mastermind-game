package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.Random;

public class CodePeg {
    private final ObjectProperty<Color> color;
    private PegColor pegColor;
    private final ObjectProperty<Boolean> isActive;

    public CodePeg(Code myGuess) {
        pegColor = PegColor.GRAY;
        color = new SimpleObjectProperty<>(pegColor.toColor());
        isActive = new SimpleObjectProperty<>(false);
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Boolean> isActiveObjectProperty() {
        return isActive;
    }

    public Boolean isActive() {
        return isActive.get();
    }

    public void activate() {
        isActive.setValue(true);
    }

    public void deactivate() {
        isActive.setValue(false);
    }

    public void cycleColor() {
        pegColor = pegColor.next();
        this.color.setValue(pegColor.toColor());
    }
}
