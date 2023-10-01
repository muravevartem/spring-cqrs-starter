package ru.muravev.cqrs.es.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;
import ru.muravev.cqrs.component.Bus;
import ru.muravev.cqrs.es.EventSourcePersistence;
import ru.muravev.cqrs.es.replayer.AggregateEvent;
import ru.muravev.cqrs.es.replayer.EventSourceReplayer;

@Component
@RequiredArgsConstructor
public class EventBusImpl implements EventBus {
    private final Bus bus;
    private final EventSourcePersistence eventSourcePersistence;
    private final EventSourceReplayer eventSourceReplayer;

    @Override
    public <T> void publish(AggregateRoot<?> root, T event) {
        eventSourceReplayer.replay(root, event);
        eventSourcePersistence.save(root, event);
        bus.publish(new AggregateEvent<>(root, event));
    }

}
