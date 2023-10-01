package ru.muravev.cqrs.es.typeresolver;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.es.description.EventDescription;
import ru.muravev.cqrs.es.description.EventDescriptionFactory;
import ru.muravev.cqrs.es.customizer.EventTypeCustomizer;
import ru.muravev.cqrs.es.exception.UnknownEventTypeException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class EventTypeResolverImpl implements EventTypeResolver {

    private final Map<EventDescription, Class<?>> descriptionToClassMap = new HashMap<>();
    private final Map<Class<?>, EventDescription> classToDescriptionMap = new HashMap<>();

    private final EventDescriptionFactory eventDescriptionFactory;


    @Autowired
    public void customize(EventTypeCustomizer customizer) {
        Class<?>[] eventTypes = customizer.getTypes();
        for (Class<?> eventType : eventTypes) {
            EventDescription eventDescription = eventDescriptionFactory.createByEventType(eventType);
            descriptionToClassMap.put(eventDescription, eventType);
            classToDescriptionMap.put(eventType, eventDescription);
        }
    }

    @Override
    public Class<?> resolveType(EventDescription eventDescription) {
        Class<?> eventType = descriptionToClassMap.get(eventDescription);
        if (isNull(eventType))
            throw new UnknownEventTypeException(eventDescription);
        return eventType;
    }

    @Override
    public EventDescription getDescription(Class<?> eventType) {
        EventDescription eventDescription = classToDescriptionMap.get(eventType);
        if (isNull(eventDescription))
            throw new UnknownEventTypeException(eventType);
        return eventDescription;
    }
}
