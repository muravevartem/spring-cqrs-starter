package ru.muravev.cqrs.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.muravev.cqrs.command.component.CommandBus;
import ru.muravev.cqrs.example.command.CreateUser;
import ru.muravev.cqrs.example.model.UserCreationDto;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CommandBus commandBus;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@RequestBody UserCreationDto dto) {
        commandBus.apply(
                new CreateUser(
                        dto.tel(),
                        dto.firstName(),
                        dto.lastName()
                )
        );
    }
}
