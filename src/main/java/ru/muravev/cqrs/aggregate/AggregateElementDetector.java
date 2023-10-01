package ru.muravev.cqrs.aggregate;

import java.lang.reflect.Field;

public interface AggregateElementDetector<T> {
    void detect(T field, AggregateDescription aggregateDescription);
}
