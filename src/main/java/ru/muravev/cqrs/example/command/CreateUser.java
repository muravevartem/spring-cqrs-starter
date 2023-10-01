package ru.muravev.cqrs.example.command;


public record CreateUser(
        String tel,
        String firstName,
        String lastName
) {
}
