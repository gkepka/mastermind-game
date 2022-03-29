package events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameExitEvent extends Event {
    public static EventType<GameExitEvent> GAME_EXIT;

    static {
        GAME_EXIT = new EventType<>("GAME_EXIT");
    }

    public GameExitEvent() {
        super(GAME_EXIT);
    }
}
