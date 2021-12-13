package model;

import javafx.scene.paint.Color;
import java.util.Random;

public enum CodePegColor {
    GRAY,
    RED,
    GOLD,
    TEAL,
    CYAN,
    GREEN;

    private static final int length = values().length;
    private static final Random random = new Random();

    public static CodePegColor getRandomColor() {
        return values()[random.nextInt(length-1)+1]; // skips GRAY
    }

    public Color toColor() {
        return switch (this) {
            case GRAY  -> Color.GRAY;
            case RED   -> Color.ORANGERED;
            case GOLD  -> Color.GOLD;
            case TEAL  -> Color.TEAL;
            case CYAN  -> Color.CYAN;
            case GREEN -> Color.LAWNGREEN;
        };
    }

    public CodePegColor next() {
        return values()[(this.ordinal() % (length-1))+1]; // skips GRAY
    }

    public CodePegColor prev() {
        if (this == GRAY) return values()[length-1];
        return values()[length-this.ordinal()];
    }
}
