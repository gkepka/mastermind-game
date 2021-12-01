package model;

public class Game {
    private Player player;
    private Object result;

    public Game(Player player, int result){
        this.player = player;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }
}
