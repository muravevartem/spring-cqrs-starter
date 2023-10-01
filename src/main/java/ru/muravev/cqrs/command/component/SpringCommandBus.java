package ru.muravev.cqrs.command.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.command.AggregateCommand;
import ru.muravev.cqrs.component.Bus;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringCommandBus implements CommandBus {
    private final Bus bus;


    @Override
    public <T> void apply(T command) {
        bus.publish(command);
    }

    @Override
    public void apply(AggregateCommand<?, ?> aggregateCommand) {
        bus.publish(aggregateCommand);
    }

    @Override
    public <T> void apply(long aggregateId, Class<?> aggregateType, T command) {

    }
}
