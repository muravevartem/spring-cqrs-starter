package ru.muravev.cqrs.es;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;
import ru.muravev.cqrs.aggregate.Projector;
import ru.muravev.cqrs.aggregate.component.ProjectFactory;
import ru.muravev.cqrs.es.replayer.EventSourceReplayer;

import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ProjectorImpl implements Projector {
    private final EventSourcePersistence eventSourcePersistence;
    private final ProjectFactory projectFactory;
    private final EventSourceReplayer replayer;

    @Override
    public <T extends AggregateRoot<?>> Optional<T> getById(long aggregateId, Class<T> aggregateType) {
        T aggregate = projectFactory.create(aggregateType);
        try (Stream<?> streamEvents = eventSourcePersistence.streamEvents(aggregateId, aggregateType)) {
            streamEvents.forEach(
                    event -> replayer.replay(aggregate, event)
            );
        }
        if (aggregate.getVersion() == 0)
            return Optional.empty();
        return Optional.of(aggregate);
    }
}
