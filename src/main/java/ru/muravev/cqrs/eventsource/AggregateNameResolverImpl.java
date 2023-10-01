package ru.muravev.cqrs.eventsource;

import org.springframework.stereotype.Component;
import ru.muravev.cqrs.aggregate.Aggregate;

import static ru.muravev.cqrs.util.TuppleUtils.firstNotEmpty;

@Component
public class AggregateNameResolverImpl implements AggregateNameResolver {

    @Override
    public String resolveNameByType(Class<?> aggregateType) {
        Aggregate aggregateAnno = aggregateType.getAnnotation(Aggregate.class);
        return firstNotEmpty(
                aggregateAnno.name(),
                aggregateAnno.value(),
                aggregateType.getSimpleName()
        );
    }
}
