package ru.muravev.cqrs.aggregate;

public interface AggregateDescriptor {
    AggregateDescription createDescription(Class<?> aggregateType);
}
