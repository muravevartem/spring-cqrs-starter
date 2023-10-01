package ru.muravev.cqrs.command;


import lombok.Getter;
import ru.muravev.cqrs.AggregateRoot;


@Getter
public class AggregateCommand<AGGR extends AggregateRoot, CMD> extends Command<CMD> {
    private final AGGR aggregate;


    public AggregateCommand(AGGR aggregate, CMD source) {
        super(source);
        this.aggregate = aggregate;
    }

}
