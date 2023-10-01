package ru.muravev.cqrs.es.replayer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;
import ru.muravev.cqrs.component.Bus;

@Component
@RequiredArgsConstructor
public class EventSourceReplayerImpl implements EventSourceReplayer {
    private final Bus bus;

    @Override
    public <T> void replay(AggregateRoot<?> root, T event) {
        bus.publish(new AggregateEventSource<>(root, event));
    }
}
