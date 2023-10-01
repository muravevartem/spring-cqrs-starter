package ru.muravev.cqrs.es.replayer;

import lombok.Data;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import ru.muravev.cqrs.AggregateRoot;

@Data
public final class AggregateEventSource<AGGR extends AggregateRoot<?>, EVENT> implements ResolvableTypeProvider {
    private final AGGR aggregate;
    private final EVENT event;


    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                AggregateEventSource.class,
                ResolvableType.forClass(aggregate.getClass()),
                ResolvableType.forClass(event.getClass())
        );
    }
}
