package ru.muravev.cqrs.es;

import ru.muravev.cqrs.AggregateRoot;

import java.util.List;
import java.util.stream.Stream;

public interface EventSourcePersistence {

    Stream<?> streamEvents(long aggregateId, Class<?> aggregateType);

    <AGGR, T> void save(AGGR root, T event);
}
