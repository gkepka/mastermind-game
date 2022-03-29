package model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final ArrayList<Guess> guesses = new ArrayList<>();
    private final Code code = new Code(false);
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
        System.out.println("The code is: " + code);
    }

    public List<HintPegStatus> verifyGuess(Guess guess) {
        var status = new ArrayList<HintPegStatus>(HintPeg.PEGS_COUNT);
        int correctPegs = 0;

        // copy the lists
        var guessCode = new ArrayList<>(guess.getCode().getCodePegs());
        var correctCode = new ArrayList<>(code.getCodePegs());

        // iterators, because you can't remove from list while iterating with a for loop
        var guessCodeIterator = guessCode.listIterator();
        var correctCodeIterator = correctCode.listIterator();

        // first pass - same index pegs have same color
        while (correctCodeIterator.hasNext() && guessCodeIterator.hasNext()) {
            CodePeg correctCodePeg = correctCodeIterator.next();
            CodePeg guessCodePeg = guessCodeIterator.next();
            if(correctCodePeg.isSameColor(guessCodePeg)) {
                correctCodeIterator.remove();
                guessCodeIterator.remove();
                status.add(HintPegStatus.CORRECT);
                correctPegs++;
            }
        }

        if (correctPegs == Code.PEGS_COUNT) {
            game.finishGame(true);
            return status;
        }

        // second pass - same color among any that are left
        correctCode.forEach(correctCodePeg -> {
            var optional = guessCode.stream()
                    .filter(guessPeg -> guessPeg.isSameColor(correctCodePeg))
                    .findAny();

            if (optional.isPresent()) {
                status.add(HintPegStatus.CORRECT_COLOR);
                guessCode.remove(optional.get());
            }
        });

        while(status.size() < HintPeg.PEGS_COUNT)
            status.add(HintPegStatus.WRONG);

        Collections.shuffle(status);

        nextGuess();
        return status;
    }

    private void nextGuess() {
        guesses.get(currentGuessIdx++).setActive(false);

        if (currentGuessIdx < guessCount) {
            guesses.get(currentGuessIdx).setActive(true);
        } else {
            game.finishGame(true);
        }

    }

    public Guess getGuess(int index) {
        return guesses.get(index);
    }

    public ArrayList<Guess> getGuesses() {
        return guesses;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public int getFailedGuesses() {
        return currentGuessIdx;
    }
}
