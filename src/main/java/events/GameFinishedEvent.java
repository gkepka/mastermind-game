package events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameFinishedEvent extends Event {
    public static final EventType<GameFinishedEvent> GAME_FINISHED;

    static {
        GAME_FINISHED = new EventType<>("GAME_FINISHED");
    }

    private int result;

    public GameFinishedEvent(int result) {
        super(GameFinishedEvent.GAME_FINISHED);

        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
