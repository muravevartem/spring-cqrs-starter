package ru.muravev.cqrs.es.description;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventDescriptionFactoryImpl implements EventDescriptionFactory {
    private final EventVersionResolver eventVersionResolver;
    private final EventNameResolver eventNameResolver;

    @Override
    public EventDescription createByEventType(Class<?> eventType) {
        return new EventDescription(
                eventNameResolver.getNameByEventType(eventType),
                eventVersionResolver.getVersionByEventType(eventType)
        );
    }
}
