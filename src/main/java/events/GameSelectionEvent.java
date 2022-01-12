package events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameSelectionEvent extends Event {
    public static final EventType<GameSelectionEvent> GAME_SELECTION;

    static {
        GAME_SELECTION = new EventType<>("GAME_SELECTION");
    }

    private final int gameDifficulty;

    public GameSelectionEvent(int difficulty) {
        super(GameSelectionEvent.GAME_SELECTION);

        this.gameDifficulty = difficulty;
    }

    public int getGameDifficulty() {
        return gameDifficulty;
    }
}
