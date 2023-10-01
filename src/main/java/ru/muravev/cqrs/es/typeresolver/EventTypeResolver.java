package ru.muravev.cqrs.es.typeresolver;

import ru.muravev.cqrs.es.description.EventDescription;

public interface EventTypeResolver {
    Class<?> resolveType(EventDescription eventDescription);

    EventDescription getDescription(Class<?> eventType);
}
