package ru.muravev.cqrs.eventsource;

@FunctionalInterface
public interface Handler {
    void handle(Object obj1, Object obj2);
}
