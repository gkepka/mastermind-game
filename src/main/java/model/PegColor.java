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
        switch (this) {

            case GRAY -> {
                return Color.GRAY;
            }
            case RED -> {
                return Color.RED;
            }
            case YELLOW -> {
                return Color.YELLOW;
            }
            case WHITE -> {
                return Color.WHITE;
            }
            case BLACK -> {
                return Color.BLACK;
            }
            case GREEN -> {
                return Color.GREEN;
            }
            default -> {return Color.PINK;}
        }
    }

    public PegColor next() {
        return values()[(this.ordinal()+1) % values().length];
    }
}
