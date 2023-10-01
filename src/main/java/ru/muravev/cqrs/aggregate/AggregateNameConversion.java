package ru.muravev.cqrs.aggregate;

import ru.muravev.cqrs.AggregateRoot;

public interface AggregateNameConversion {
    String getNameByAggregateType(Class<? extends AggregateRoot> aggregateType);
}
