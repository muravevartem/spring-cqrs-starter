package ru.muravev.cqrs.es.description;

public interface EventNameResolver {
    String getNameByEventType(Class<?> eventType);
}
