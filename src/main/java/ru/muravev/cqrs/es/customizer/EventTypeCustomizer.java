package ru.muravev.cqrs.es.customizer;

import org.reflections.Reflections;
import ru.muravev.cqrs.es.annotation.Event;

import java.util.Set;

@FunctionalInterface
public interface EventTypeCustomizer {
    Class<?>[] getTypes();

    static EventTypeCustomizer ofPackages(String ... packages) {
        return () -> {
            Reflections reflections = new Reflections((Object[]) packages);
            Set<Class<?>> eventTypes = reflections.getTypesAnnotatedWith(Event.class);
            return eventTypes.toArray(Class[]::new);
        };
    }

}
