package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


public class CodePeg {
    private final ObjectProperty<Color> color;
    private PegColor pegColor;

    public CodePeg(Code myGuess) {
        pegColor = PegColor.GRAY;
        color = new SimpleObjectProperty<>(pegColor.toColor());
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void cycleColor() {
        pegColor = pegColor.next();
        this.color.setValue(pegColor.toColor());
    }
}
