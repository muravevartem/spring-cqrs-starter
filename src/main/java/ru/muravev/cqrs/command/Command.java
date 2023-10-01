package ru.muravev.cqrs.command;

import lombok.Getter;

@Getter
public class Command<CMD> {
    private final CMD source;

    public Command(CMD source) {
        this.source = source;
    }

}
