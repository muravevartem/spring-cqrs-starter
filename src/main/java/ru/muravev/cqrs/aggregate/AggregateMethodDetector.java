package ru.muravev.cqrs.aggregate;

import java.lang.reflect.Method;

public interface AggregateMethodDetector {
    void detect(Method method, AggregateDescription aggregateDescription);
}
