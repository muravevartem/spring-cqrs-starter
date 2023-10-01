package ru.muravev.cqrs.es.exception;

public class EventSourceException extends RuntimeException {
    public EventSourceException() {
    }

    public EventSourceException(String message) {
        super(message);
    }

    public EventSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventSourceException(Throwable cause) {
        super(cause);
    }
}
