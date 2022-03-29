package events;

import javafx.event.Event;
import javafx.event.EventType;

public class PegClickedEvent extends Event {
    public static final EventType<PegClickedEvent> PEG_CLICKED = new EventType<>("PEG CLICKED");

    public PegClickedEvent() {
        super(PEG_CLICKED);
    }
}
