package ru.muravev.cqrs.es.exception;

public class EventSerializationException extends EventSourceException {
    public EventSerializationException() {
    }

    public EventSerializationException(Throwable cause) {
        super(cause);
    }
}
