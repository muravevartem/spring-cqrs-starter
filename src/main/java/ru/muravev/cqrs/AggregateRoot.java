package ru.muravev.cqrs;

public interface AggregateRoot<ID> {
    ID getId();

    long getVersion();
}
