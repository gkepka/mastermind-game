package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class Guess {

    private final Code myCode;
    private final Board board;
    private List<HintPeg> hints;
    private final ObjectProperty<Boolean> isActive;

    private boolean verified = false;

    public Guess(Board board, Code myCode) {
        this.board = board;
        this.myCode = myCode;
        this.isActive = new SimpleObjectProperty<>(false);
    }

    public void verifyGuess() {
        if (verified) return;
        myCode.deactivatePegs();
        // TODO: board niech zwraca listę stringów czy enumów jakichś
        Object result = board.verifyGuess(this);
//        hints = ???
        verified = true;
    }

    public ObjectProperty<Boolean> isActiveObjectProperty() {
        return isActive;
    }

    public Boolean isActive() {
        return isActive.get();
    }

    public void activate() {
        isActive.setValue(true);
    }

    public void deactivate() {
        isActive.setValue(false);
    }

    public boolean isVerified() {
        return verified;
    }

    public Code getMyCode() {
        return myCode;
    }

}
