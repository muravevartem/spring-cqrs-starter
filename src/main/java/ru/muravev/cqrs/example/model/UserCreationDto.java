package ru.muravev.cqrs.example.model;

public record UserCreationDto(
        String tel,
        String firstName,
        String lastName
) {
}
