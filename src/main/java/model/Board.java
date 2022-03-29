package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Board {
    public static final int GUESS_COUNT = 12;
    private final ArrayList<Guess> guesses = new ArrayList<>(GUESS_COUNT);
    private final Code code = new Code();
    private IntegerProperty currentGuess;

    public Board() {
        // TODO:
        for (int i = 0; i < GUESS_COUNT; i++) {
            guesses.add(new Guess(this, new Code()));
        }
        currentGuess = new SimpleIntegerProperty(0);
    }

    public Object verifyGuess(Guess guess) {
        // TODO:
        // board niech zwraca listę HintPeg, lub stringów czy enumów jakichś
        // które w Guess będą przerabiane na HintPegi
        currentGuess.set(currentGuess.get()+1);
        return null;
    }

    public Guess getGuess(int index) {
        return guesses.get(index);
    }

    public IntegerProperty getCurrentGuessProperty() {
        return currentGuess;
    }

    public Integer getCurrentGuess() {
        return currentGuess.get();
    }

    public void deactivateGuess(int index) {
        guesses.get(index).deactivate();
    }
}
