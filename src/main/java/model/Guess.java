package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.List;

public class Guess {
    private final Code myCode;
    private final Board board;
    private List<HintPeg> hints;
    private final BooleanProperty active = new SimpleBooleanProperty(false);
    private boolean verified = false;

    public Guess(Board board) {
        this.board = board;
        this.myCode = new Code(this);
    }

    public void verifyGuess() {
        if (!isActive() || verified) return;

        // TODO: board niech zwraca listę stringów czy enumów jakichś
        Object result = board.verifyGuess(this);
//        hints = ???
        verified = true;
        setActive(false);
        System.out.println("verified");
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

    public Code getMyCode() {
        return myCode;
    }

}
