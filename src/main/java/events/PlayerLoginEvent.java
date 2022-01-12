package events;

import javafx.event.Event;
import javafx.event.EventType;
import model.game.Player;

public class PlayerLoginEvent extends Event {
    public static final EventType<PlayerLoginEvent> PLAYER_LOGIN;

    static {
        PLAYER_LOGIN = new EventType<>("PLAYER_LOGIN");
    }

    private final Player player;

    public PlayerLoginEvent(Player player) {
        super(PlayerLoginEvent.PLAYER_LOGIN);

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
