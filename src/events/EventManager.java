package events;

public class EventManager {
    private final EventBus eventBus;

    public EventManager(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
