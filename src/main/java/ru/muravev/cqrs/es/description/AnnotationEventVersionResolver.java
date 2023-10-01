package ru.muravev.cqrs.es.description;

import org.springframework.stereotype.Component;
import ru.muravev.cqrs.es.annotation.Event;

import static java.util.Objects.isNull;
import static ru.muravev.cqrs.util.ObjectUtility.getFirstNotNull;

@Component
public class AnnotationEventVersionResolver implements EventVersionResolver {

    @Override
    public String getVersionByEventType(Class<?> eventType) {
        Event eventAnno = eventType.getAnnotation(Event.class);
        if (isNull(eventAnno))
            return null;

        return getFirstNotNull(eventAnno.version())
                .orElse("1.0");
    }
}
