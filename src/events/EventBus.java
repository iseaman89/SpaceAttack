package events;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public <T> void unsubscribe(Class<T> eventType, Consumer<T> listener) {
        var list = listeners.get(eventType);
        if (list != null) {
            list.remove(listener);
        }
    }

    public <T> void publish(T event) {
        var list = listeners.getOrDefault(event.getClass(), List.of());
        for (var l : new ArrayList<>(list)) {
            ((Consumer<T>) l).accept(event);
        }
    }
}
