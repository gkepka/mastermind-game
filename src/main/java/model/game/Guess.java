package model.game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class Guess {
    private final Code code;
    private final Board board;
    private final List<HintPeg> hints = new ArrayList<>(HintPeg.PEGS_COUNT);
    private final BooleanProperty active = new SimpleBooleanProperty(false);
    private boolean verified = false;

    public Guess(Board board) {
        this.board = board;
        this.code = new Code(true);

        for (int i = 0; i < HintPeg.PEGS_COUNT; i++) {
            hints.add(new HintPeg());
        }
    }

    public void verifyGuess() {
        if (!isActive() || verified) return;
        var result = board.verifyGuess(this);

        for (int i = 0; i < HintPeg.PEGS_COUNT; i++) {
            hints.get(i).setStatus(result.get(i));
        }

        verified = true;
        setActive(false);
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public boolean isActive() {
        return active.get();
    }

    public void setActive(boolean active) {
        this.active.setValue(active);
    }

    public Code getCode() {
        return code;
    }

    public List<HintPeg> getHints() {
        return hints;
    }

}
