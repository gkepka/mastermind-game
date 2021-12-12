package model;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Guess> guesses = new ArrayList<>();
    private final Code code = new Code();
    private final Game game;
    private final int guessCount;
    private int currentGuessIdx = 0;

    public Board(int guessCount, Game game) {
        this.game = game;
        this.guessCount = guessCount;

        for (int i = 0; i < guessCount; i++) {
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
        guesses.get(currentGuessIdx).setActive(false);
        currentGuessIdx++;
        if (currentGuessIdx < guessCount) {
            guesses.get(currentGuessIdx).setActive(true);
        } else {
            game.setGameOver(true);
        }
    }

    public Guess getGuess(int index) {
        return guesses.get(index);
    }

    public int getGuessCount() {
        return guessCount;
    }

    public int getFailedGuesses() {
        return currentGuessIdx;
    }
}
