package ru.muravev.cqrs.aggregate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.eventsource.AggregateNameResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AggregateDescriptorImpl implements AggregateDescriptor {
    private final List<AggregateElementDetector<Method>> methodDetectors;
    private final List<AggregateElementDetector<Field>> fieldDetectors;
    private final AggregateNameResolver nameResolver;


    @Override
    public AggregateDescription createDescription(Class<?> aggregateType) {
        String aggregateName = nameResolver.resolveNameByType(aggregateType);
        AggregateDescription aggregateDescription = new AggregateDescription(aggregateName, aggregateType);
        for (Method method : aggregateType.getMethods()) {
            methodDetectors.forEach(detector -> detector
                    .detect(method, aggregateDescription));
        }
        for (Field field : aggregateType.getDeclaredFields()) {
            fieldDetectors.forEach(detector -> detector
                    .detect(field, aggregateDescription));
        }

        validateDescription(aggregateDescription);

        return aggregateDescription;
    }

    private void validateDescription(AggregateDescription description) {
        if (description.getIdentityGetter() == null)
            throw new IllegalArgumentException("Not found id getter");
    }
}
