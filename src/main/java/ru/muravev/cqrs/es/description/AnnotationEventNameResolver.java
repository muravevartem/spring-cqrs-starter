package ru.muravev.cqrs.es.description;

import org.springframework.stereotype.Component;
import ru.muravev.cqrs.es.annotation.Event;

import static java.util.Objects.isNull;
import static ru.muravev.cqrs.util.ObjectUtility.getFirstNotNull;

@Component
public class AnnotationEventNameResolver implements EventNameResolver {

    @Override
    public String getNameByEventType(Class<?> eventType) {
        Event eventAnno = eventType.getAnnotation(Event.class);
        if (isNull(eventAnno))
            return getDefaultNameByEventType(eventType);

        return getFirstNotNull(eventAnno.name(), eventAnno.value())
                        .orElseGet(() -> getDefaultNameByEventType(eventType));
    }

    private String getDefaultNameByEventType(Class<?> eventType) {
        return eventType.getSimpleName();
    }
}
