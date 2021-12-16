package model;

public class Game {
    public static final int INITIAL_POINTS = 1000;

    private final Player player;
    private final Board board;
    private int result = INITIAL_POINTS;
    private boolean gameOver = false;
    private Long id;

    public Game(Player player, int guessCount){
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        calculatePoints();
        System.out.println("Game over!");
        System.out.println("Points: " + result);
    }

    private void calculatePoints() {
        result -= 1000*(((double) board.getFailedGuesses())/ board.getGuessCount());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
