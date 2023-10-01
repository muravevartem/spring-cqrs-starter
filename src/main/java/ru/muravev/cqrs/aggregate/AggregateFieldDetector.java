package ru.muravev.cqrs.aggregate;

import java.lang.reflect.Field;

public interface AggregateFieldDetector {
    void detect(Field field, AggregateDescription aggregateDescription);
}
