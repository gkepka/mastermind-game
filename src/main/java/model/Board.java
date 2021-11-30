package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int GUESS_COUNT = 12;
    private final ArrayList<Guess> guesses = new ArrayList<>(GUESS_COUNT);
    private final Code code = new Code();

    public Board() { // TODO:
        for (int i = 0; i<GUESS_COUNT; i++) {
            guesses.add(new Guess(this, new Code())); // nie wiem co to miało być, zajebałem się w akcji
        }
    }

    public Object verifyGuess(Guess guess) { // TODO:
        return null; // board niech zwraca listę HintPeg, lub stringów czy enumów jakichś
                     // które w Guess będą przerabiane na HintPegi
    }


    public Guess getGuess(int index) {
        return guesses.get(index);
    }
}
