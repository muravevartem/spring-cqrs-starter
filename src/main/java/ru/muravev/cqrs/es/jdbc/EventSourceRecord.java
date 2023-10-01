package ru.muravev.cqrs.es.jdbc;

import java.time.Instant;
import java.util.UUID;


public record EventSourceRecord(
        UUID eventId,
        long localId,
        String eventType,
        String eventVersion,
        String aggregateId,
        String aggregateType,
        String serializedData,
        Instant createdAt,
        String createdBy
) {
}
