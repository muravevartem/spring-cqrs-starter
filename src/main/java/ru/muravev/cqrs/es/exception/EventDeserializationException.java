package ru.muravev.cqrs.es.exception;

public class EventDeserializationException extends EventSourceException {
    public EventDeserializationException() {
    }

    public EventDeserializationException(Throwable cause) {
        super(cause);
    }
}
