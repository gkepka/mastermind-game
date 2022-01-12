package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Game {
    public static final int INITIAL_POINTS = 1000;

    private final Player player;
    private final Board board;
    private int result = INITIAL_POINTS;

    private final BooleanProperty over = new SimpleBooleanProperty(false);
    private Long id;

    public Game(Player player, int guessCount) {
        this.player = player;
        this.board = new Board(guessCount, this);
    }

    public int getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public void finishGame(boolean gameOver) {
        this.over.set(gameOver);

        this.calculatePoints();
        System.out.println("Points: " + result);
    }

    public BooleanProperty overProperty() {
        return over;
    }

    private void calculatePoints() {
        result -= INITIAL_POINTS * (((double) board.getFailedGuesses()) / board.getGuessCount());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
