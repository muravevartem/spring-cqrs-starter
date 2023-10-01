package ru.muravev.cqrs.eventsource;

@FunctionalInterface
public interface Getter {
    Object get(Object object);
}
