package ru.muravev.cqrs.example.event;

import ru.muravev.cqrs.es.annotation.Event;

import java.util.UUID;

@Event(name = "USER_CREATED", version = "1.1")
public record UserCreated(
        UUID id,
        String firstName,
        String lastName,
        String tel
) {
}
