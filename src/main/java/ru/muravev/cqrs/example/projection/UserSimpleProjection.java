package ru.muravev.cqrs.example.projection;

import lombok.Data;
import ru.muravev.cqrs.aggregate.annotation.Aggregate;
import ru.muravev.cqrs.es.annotation.EventSourceFilter;
import ru.muravev.cqrs.example.command.CreateUser;

import java.util.UUID;

@EventSourceFilter(only = CreateUser.class)
@Aggregate(name = "USER")
@Data
public class UserSimpleProjection {
    private UUID uuid;
    private String tel;
}
