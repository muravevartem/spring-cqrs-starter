package ru.muravev.cqrs.aggregate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.eventsource.Getter;
import ru.muravev.cqrs.eventsource.HandlerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class AggregateIdentityDetector implements AggregateElementDetector<Field> {
    private final HandlerFactory handlerFactory;


    @Override
    public void detect(Field field, AggregateDescription aggregateDescription) {
        if (!field.isAnnotationPresent(AggregateId.class))
            return;
        Class<?> aggregateType = field.getDeclaringClass();
        String propertyName = "get" + field.getName();
        Method identityMethod = Arrays.stream(aggregateType.getMethods())
                .filter(method -> propertyName.equalsIgnoreCase(method.getName()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        Getter idGetter = handlerFactory.createGetterOnMethod(identityMethod);
        aggregateDescription.setIdentityGetter(idGetter);
    }
}
