package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


public class CodePeg {
    private final ObjectProperty<Color> color;
    private CodePegColor codePegColor;

    public CodePeg(Guess myGuess) {
        codePegColor = CodePegColor.GRAY;
        color = new SimpleObjectProperty<>(codePegColor.toColor());
    }

    public CodePeg() {
        codePegColor = CodePegColor.getRandomColor();
        color = new SimpleObjectProperty<>(codePegColor.toColor());
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void cycleColor() {
        codePegColor = codePegColor.next();
        this.color.setValue(codePegColor.toColor());
    }

    @Override
    public String toString() {
        return codePegColor.name();
    }


    public boolean isSameColor(CodePeg other) {
        return other.getColor().equals(getColor());
    }
}
