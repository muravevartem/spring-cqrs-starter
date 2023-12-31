package ru.muravev.cqrs.example.event;

import java.util.UUID;

public record UserCreated(
        UUID uuid,
        String tel,
        String email,
        String firstName,
        String lastName
) {
}
