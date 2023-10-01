package ru.muravev.cqrs.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;
import ru.muravev.cqrs.aggregate.annotation.Aggregate;

import static java.util.Objects.isNull;
import static ru.muravev.cqrs.util.ObjectUtility.getFirstNotNull;

@Component
@RequiredArgsConstructor
public class AnnotationAggregateNameConversion implements AggregateNameConversion {

    @Override
    public String getNameByAggregateType(Class<? extends AggregateRoot> aggregateType) {
        Aggregate aggregateAnno = aggregateType.getAnnotation(Aggregate.class);
        if (isNull(aggregateAnno))
            return getDefaultNameByAggregateType(aggregateType);

        return getFirstNotNull(aggregateAnno.name(), aggregateAnno.value())
                .orElseGet(() -> getDefaultNameByAggregateType(aggregateType));
    }

    private String getDefaultNameByAggregateType(Class<? extends AggregateRoot> aggregateType) {
        return aggregateType.getSimpleName();
    }
}
