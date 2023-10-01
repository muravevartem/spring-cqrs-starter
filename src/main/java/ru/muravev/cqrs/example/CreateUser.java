package ru.muravev.cqrs.example;

import java.util.UUID;

public record CreateUser(
        UUID uuid,
        String tel,
        String email,
        String firstName,
        String lastName
) {
}
