package model;

import java.util.ArrayList;

public class Board {
    public static final int GUESS_COUNT = 12;
    private final ArrayList<Guess> guesses = new ArrayList<>(GUESS_COUNT);
    private final Code code = new Code();
    private int currentGuess = 0;

    public Board() {
        // TODO:
        for (int i = 0; i < GUESS_COUNT; i++) {
            guesses.add(new Guess(this));
        }
        guesses.get(0).setActive(true);
        System.out.println("The code is: " + code.toString());
    }

    public Object verifyGuess(Guess guess) {
        // TODO:
        // board niech zwraca listę HintPeg, lub stringów czy enumów jakichś
        // które w Guess będą przerabiane na HintPegi
        nextGuess();
        return null;
    }

    private void nextGuess() {
        guesses.get(currentGuess).setActive(false);
        currentGuess++; // TODO: weryfikacja czy jest max.
        guesses.get(currentGuess).setActive(true);
//        if (guessCount < guesses.size()) {
//            currentGuess.deactivate();
//            currentGuess = guesses.get(guessCount);
//        } else {
//            // TODO: wyświetlanie kodu
//            System.out.println("Last check");
//        }
    }

    public Guess getGuess(int index) {
        return guesses.get(index);
    }
}
