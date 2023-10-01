package ru.muravev.cqrs.es.description;

public interface EventDescriptionFactory {
    EventDescription createByEventType(Class<?> eventType);
}
