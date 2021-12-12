package model;

import javafx.scene.paint.Color;
import java.util.Random;

public enum PegColor {
    GRAY,
    RED,
    YELLOW,
    WHITE,
    BLACK,
    GREEN;

    private static final int length = values().length;
    private static final Random random = new Random();

    public static PegColor getRandomColor() {
        return values()[random.nextInt(length-1)+1]; // skips GRAY
    }

    public Color toColor() {
        return switch (this) {
            case GRAY   -> Color.GRAY;
            case RED    -> Color.RED;
            case YELLOW -> Color.YELLOW;
            case WHITE  -> Color.WHITE;
            case BLACK  -> Color.BLACK;
            case GREEN  -> Color.GREEN;
            default     -> Color.PINK;
        };
    }

    public PegColor next() {
        return values()[(this.ordinal() % (length-1))+1]; // skips GRAY
    }
}
