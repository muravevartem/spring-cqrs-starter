package ru.muravev.cqrs.es.jdbc;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

@Component
public class EventSourceRecordRowMapper implements RowMapper<EventSourceRecord> {

    @Override
    public EventSourceRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        String eventId = rs.getString("event_id");
        long localEventId = rs.getLong("local_event_id");
        String eventType = rs.getString("event_type");
        String eventVersion = rs.getString("event_version");
        String aggregateId = rs.getString("aggregate_id");
        String aggregateType = rs.getString("aggregate_type");
        String serializedData = rs.getString("serialized_data");
        Timestamp createdAt = rs.getTimestamp("created_at");
        String createdBy = rs.getString("created_by");

        return new EventSourceRecord(
                UUID.fromString(eventId),
                localEventId,
                eventType,
                eventVersion,
                aggregateId,
                aggregateType,
                serializedData,
                createdAt.toInstant(),
                createdBy
        );
    }
}
