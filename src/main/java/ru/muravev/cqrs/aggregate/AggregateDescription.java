package ru.muravev.cqrs.aggregate;

import lombok.Builder;
import lombok.Data;
import ru.muravev.cqrs.eventsource.Getter;
import ru.muravev.cqrs.eventsource.Handler;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

@Data
public class AggregateDescription {
    private final String name;
    private final Class<?> type;
    private Getter identityGetter;
    private final Map<Class<?>, Handler> eventSourceHandlers = new IdentityHashMap<>();
    private final Map<Class<?>, Handler> commandHandlers = new IdentityHashMap<>();


    public void addEventSourceHandler(Class<?> eventType, Handler handler) {
        eventSourceHandlers.put(eventType, handler);
    }

    public void addCommandHandler(Class<?> commandType, Handler handler) {
        commandHandlers.put(commandType, handler);
    }
}
