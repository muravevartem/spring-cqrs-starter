package ru.muravev.cqrs.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.aggregate.AggregateDescription;
import ru.muravev.cqrs.aggregate.AggregateElementDetector;
import ru.muravev.cqrs.eventsource.Handler;
import ru.muravev.cqrs.eventsource.HandlerFactory;
import ru.muravev.cqrs.util.MethodUtils;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
@Slf4j
public class AggregateCommandDetector implements AggregateElementDetector<Method> {
    private final HandlerFactory handlerFactory;

    @Override
    public void detect(Method method, AggregateDescription aggregateDescription) {
        if (method.isAnnotationPresent(CommandHandler.class)) {
            Handler handlerOnMethod = handlerFactory.createHandlerOnMethod(method);
            Class<?> commandType = MethodUtils.getFirstParameterType(method);
            aggregateDescription.addCommandHandler(commandType, handlerOnMethod);
        }
    }
}
