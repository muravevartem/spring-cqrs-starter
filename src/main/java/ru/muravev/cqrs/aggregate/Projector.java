package ru.muravev.cqrs.aggregate;

import ru.muravev.cqrs.AggregateRoot;

import java.util.Optional;

public interface Projector {

    <T extends AggregateRoot<?>> Optional<T> getById(long aggregateId, Class<T> aggregateType);

}
