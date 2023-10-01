package ru.muravev.cqrs.es.description;

public interface EventVersionResolver {
    String getVersionByEventType(Class<?> eventType);
}
