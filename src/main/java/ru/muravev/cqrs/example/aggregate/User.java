package ru.muravev.cqrs.example.aggregate;

import lombok.Getter;
import ru.muravev.cqrs.aggregate.Aggregate;
import ru.muravev.cqrs.aggregate.AggregateId;
import ru.muravev.cqrs.command.CommandHandler;
import ru.muravev.cqrs.example.CreateUser;
import ru.muravev.cqrs.example.event.UserCreated;
import ru.muravev.cqrs.eventsource.EventSourceHandler;

import java.util.UUID;

@Aggregate(name = "USER")
@Getter
public class User {
    @AggregateId
    private UUID uuid;
    private String tel;
    private String email;
    private String firstName;
    private String lastName;

    @CommandHandler
    public void handle(CreateUser user) {

    }

    @EventSourceHandler
    public void on(UserCreated event) {
        this.uuid = event.uuid();
        this.tel = event.tel();
        this.email = event.email();
        this.firstName = event.firstName();
        this.lastName = event.lastName();
    }

}
