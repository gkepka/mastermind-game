package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.Random;

public class CodePeg {
    private final ObjectProperty<Color> color;

    public CodePeg(Code myGuess) {
        color = new SimpleObjectProperty<>(new Color(1, 0, 1, 1)); // TODO: jakiś enum z kolorami
    }

    public ObjectProperty<Color> colorObjectProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }


    public void cycleColor() {
        Random random = new Random();
        this.color.setValue(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
        // TODO: jakiś enum z kolorami
    }
}
