package ru.muravev.cqrs.es.bus;

import ru.muravev.cqrs.AggregateRoot;

public interface EventBus {
    <T> void publish(AggregateRoot<?> root, T event);

}
