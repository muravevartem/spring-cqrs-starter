package ru.muravev.cqrs.command.component;

import ru.muravev.cqrs.command.AggregateCommand;

public interface CommandBus {

    /**
     * Применение команды для аггрегата с указанным ID и типом
     *
     * @param command объект комманды
     * @param <T> тип комманды
     */
    <T> void apply(T command);

    void apply(AggregateCommand<?, ?> aggregateCommand);

    /**
     * Применение команды для аггрегата с указанным ID и типом
     *
     * @param aggregateId ID аггрегата
     * @param aggregateType тип аггрегата
     * @param command объект комманды
     * @param <T> тип комманды
     */
    <T> void apply(long aggregateId, Class<?> aggregateType, T command);
}
