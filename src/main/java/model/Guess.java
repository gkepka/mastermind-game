package model;

import java.util.List;

public class Guess {

    private final Code myCode;
    private final Board board;
    private List<HintPeg> hints;

    private boolean verified = false;

    public Guess(Board board, Code myCode) {
        this.board = board;
        this.myCode = myCode;
    }


    public void verifyGuess() {
        if (verified) return;
        Object result = board.verifyGuess(this); // TODO: board niech zwraca listę stringów czy enumów jakichś
//        hints = ???
        verified = true;
    }

    public boolean isVerified() {
        return verified;
    }

    public Code getMyCode() {
        return myCode;
    }

}