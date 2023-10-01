package ru.muravev.cqrs.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.muravev.cqrs.example.aggregate.User;
import ru.muravev.cqrs.aggregate.AggregateDescription;
import ru.muravev.cqrs.aggregate.AggregateDescriptor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final AggregateDescriptor aggregateDescriptor;


    @GetMapping
    public AggregateDescription test() {
        AggregateDescription aggregateDescription = aggregateDescriptor.createDescription(User.class);
        return aggregateDescription;
    }
}
