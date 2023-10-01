package ru.muravev.cqrs.aggregate.component;

import ru.muravev.cqrs.AggregateRoot;

public interface ProjectFactory {
    <T extends AggregateRoot> T create(Class<T> tClass);
}
