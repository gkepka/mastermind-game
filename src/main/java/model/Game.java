package model;

public class Game {
    private Player player;
    private Board board;
    private int result;

    public Game(Player player, Board board, int result){
        this.player = player;
        this.board = board;
        this.result = result;
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
}
