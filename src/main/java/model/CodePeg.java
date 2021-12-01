package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.Random;

public class CodePeg {
    private final ObjectProperty<Color> color;

    public CodePeg(Code myGuess) {
        // TODO: jakiś enum z kolorami
        color = new SimpleObjectProperty<>(new Color(1, 0, 1, 1));
    }

    public ObjectProperty<Color> colorObjectProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void cycleColor() {
        // TODO: jakiś enum z kolorami
        Random random = new Random();
        this.color.setValue(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
    }
}
