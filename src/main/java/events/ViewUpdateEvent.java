package events;

import javafx.event.Event;
import javafx.event.EventType;


public class ViewUpdateEvent extends Event {
    public static EventType<ViewUpdateEvent> VIEW_UPDATE = new EventType<>("VIEW_UPDATE");

    public ViewUpdateEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public ViewUpdateEvent() {
        this(VIEW_UPDATE);
    }
}
