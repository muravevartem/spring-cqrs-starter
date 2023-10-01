package ru.muravev.cqrs.es.replayer;

import ru.muravev.cqrs.AggregateRoot;

public interface EventSourceReplayer {
    <T> void replay(AggregateRoot<?> root, T event);
}
