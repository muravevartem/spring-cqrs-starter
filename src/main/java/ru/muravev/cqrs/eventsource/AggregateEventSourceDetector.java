package ru.muravev.cqrs.eventsource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.aggregate.AggregateDescription;
import ru.muravev.cqrs.aggregate.AggregateElementDetector;
import ru.muravev.cqrs.util.MethodUtils;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class AggregateEventSourceDetector implements AggregateElementDetector<Method> {
    private final HandlerFactory handlerFactory;


    @Override
    public void detect(Method method, AggregateDescription aggregateDescription) {
        if (method.isAnnotationPresent(EventSourceHandler.class)) {
            Class<?> eventType = MethodUtils.getFirstParameterType(method);
            Handler handler = handlerFactory.createHandlerOnMethod(method);
            aggregateDescription.addEventSourceHandler(eventType, handler);
        }
    }
}
