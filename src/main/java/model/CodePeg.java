package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


public class CodePeg {
    private final ObjectProperty<Color> color;
    private CodePegColor codePegColor;

    public CodePeg() {
        codePegColor = CodePegColor.GRAY;
        color = new SimpleObjectProperty<>(codePegColor.toColor());
    }

    public CodePeg(CodePegColor codePegColor) {
        this.codePegColor = codePegColor;
        color = new SimpleObjectProperty<>(codePegColor.toColor());
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void nextColor() {
        setColor(codePegColor.next());
    }

    public void prevColor() {
        setColor(codePegColor.prev());
    }

    private void setColor(CodePegColor codePegColor) {
        this.codePegColor = codePegColor;
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
