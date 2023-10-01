package ru.muravev.cqrs.es.replayer;

import ru.muravev.cqrs.AggregateRoot;

public record AggregateEvent<AGGR extends AggregateRoot, EVENT>(
        AGGR aggregate,
        EVENT event
) {
}
