package model.game;

import javafx.scene.paint.Color;

public enum HintPegStatus {
    UNSET,
    WRONG,
    CORRECT_COLOR,
    CORRECT;

    public Color toColor() {
        return switch (this) {
            case UNSET         -> Color.GRAY;
            case WRONG         -> Color.RED;
            case CORRECT_COLOR -> Color.YELLOW;
            case CORRECT       -> Color.GREEN;
        };
    }
}
