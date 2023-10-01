package ru.muravev.cqrs.es.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;
import ru.muravev.cqrs.aggregate.AggregateNameConversion;
import ru.muravev.cqrs.es.EventSourcePersistence;
import ru.muravev.cqrs.es.description.EventDescription;
import ru.muravev.cqrs.es.serializer.EventSerializer;
import ru.muravev.cqrs.es.typeresolver.EventTypeResolver;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JdbcEventSourcePersistence implements EventSourcePersistence {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<EventSourceRecord> rowMapper;
    private final AggregateNameConversion aggregateNameConversion;
    private final EventSerializer eventSerializer;
    private final EventTypeResolver eventTypeResolver;


    private static final String SQL_READ_ALL_EVENTS_BY_AGGREGATE = """
            select * from event_source
            where aggregate_type = ? and aggregate_id = ?
            order by local_id
            """;

    private static final String SQL_INSERT_EVENT = """
            insert into event_source (event_id, local_event_id, event_type, event_version, aggregate_id, aggregate_type,
                                      serialized_data, created_at, created_by)
            values (?, ?, ?, ?, ?, ?, ?::jsonb, ?, ?)
            returning *
            """;


    @Override
    public Stream<?> streamEvents(long aggregateId, Class<?> aggregateType) {
        String aggregateTypeName = aggregateNameConversion.getNameByAggregateType(aggregateType);
        Stream<EventSourceRecord> eventSourceRecord = jdbcTemplate.queryForStream(
                SQL_READ_ALL_EVENTS_BY_AGGREGATE,
                rowMapper,
                aggregateTypeName,
                aggregateId
        );
        return eventSourceRecord
                .map(this::asDomainEvent);
    }

    @Override
    public <AGGR, T> void save(AGGR root, T event) {
        String aggregateType = aggregateNameConversion.getNameByAggregateType(root.getClass());
        EventDescription eventDescription = eventTypeResolver.getDescription(event.getClass());
        jdbcTemplate.query(
                SQL_INSERT_EVENT,
                rowMapper,
                UUID.randomUUID(),
                root.getVersion(),
                eventDescription.name(),
                eventDescription.version(),
                root.getId(),
                aggregateType,
                eventSerializer.serialize(event),
                Timestamp.from(Instant.now()),
                "test_user"
        );
    }

    private Object asDomainEvent(EventSourceRecord eventSourceRecord) {
        EventDescription eventDescription = getEventDescription(eventSourceRecord);
        Class<?> eventType = eventTypeResolver.resolveType(eventDescription);
        return eventSerializer.deserialize(getSerializedData(eventSourceRecord), eventType);
    }

    private static String getSerializedData(EventSourceRecord eventSourceRecord) {
        return eventSourceRecord.serializedData();
    }

    private static EventDescription getEventDescription(EventSourceRecord eventSourceRecord) {
        return new EventDescription(eventSourceRecord.eventType(), eventSourceRecord.eventVersion());
    }
}
