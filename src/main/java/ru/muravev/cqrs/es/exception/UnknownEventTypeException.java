package ru.muravev.cqrs.es.exception;

import ru.muravev.cqrs.es.description.EventDescription;

public class UnknownEventTypeException extends EventSourceException {
    public UnknownEventTypeException(EventDescription eventDescription) {
        super("Неизвестный тип события " + eventDescription.name() + ", версия " + eventDescription.version());
    }

    public UnknownEventTypeException(Class<?> eventType) {
        super("Неизвестное событие " + eventType.getCanonicalName());
    }

}
