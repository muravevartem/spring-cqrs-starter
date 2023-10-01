package ru.muravev.cqrs.eventsource;

public interface AggregateNameResolver {
    String resolveNameByType(Class<?> aggregateType);
}
