package events;

import javafx.event.Event;
import javafx.event.EventType;

public class ViewUpdateEvent extends Event {
    public static final EventType<ViewUpdateEvent> NEW_GAME;
    public static final EventType<ViewUpdateEvent> USER_LOGON;
    public static final EventType<ViewUpdateEvent> GAME_FINISHED;

    static {
        NEW_GAME = new EventType<>("NEW_GAME");
        USER_LOGON = new EventType<>("USER_LOGON");
        GAME_FINISHED = new EventType<>("GAME_FINISHED");
    }

    public ViewUpdateEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
