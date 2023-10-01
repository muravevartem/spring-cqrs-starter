package ru.muravev.cqrs.es.serializer;

public interface EventSerializer {
    <T> String serialize(T event);

    <T> T deserialize(String serializedEvent, Class<T> eventType);
}
