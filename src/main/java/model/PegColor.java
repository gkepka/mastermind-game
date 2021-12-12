package model;

import javafx.scene.paint.Color;

public enum PegColor {
    GRAY,
    RED,
    YELLOW,
    WHITE,
    BLACK,
    GREEN;

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
        return values()[(this.ordinal()+1) % values().length];
    }
}
