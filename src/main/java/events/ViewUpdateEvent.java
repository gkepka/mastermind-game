package events;

import javafx.event.Event;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ViewUpdateEvent extends Event {
    public static final EventType<ViewUpdateEvent> NEW_GAME;
    public static final EventType<ViewUpdateEvent> USER_LOGON;
    public static final EventType<ViewUpdateEvent> GAME_FINISHED;

    private Map<String, Integer> eventParemeters;

    static {
        NEW_GAME = new EventType<>("NEW_GAME");
        USER_LOGON = new EventType<>("USER_LOGON");
        GAME_FINISHED = new EventType<>("GAME_FINISHED");
    }

    public ViewUpdateEvent(EventType<? extends Event> eventType) {
        super(eventType);

        this.eventParemeters = new HashMap<>();
    }

    public void setParameter(String key, int value) {
        this.eventParemeters.put(key, value);
    }

    public Optional<Integer> getParameter(String key) {
        return Optional.ofNullable(this.eventParemeters.get(key));
    }


}
