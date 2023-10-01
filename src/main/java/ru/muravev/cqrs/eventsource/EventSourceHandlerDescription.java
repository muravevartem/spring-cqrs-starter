package ru.muravev.cqrs.eventsource;

import java.util.function.BiConsumer;

public record EventSourceHandlerDescription(
        BiConsumer<Object, Object> handler,
        Class<?> esType
) {
}
