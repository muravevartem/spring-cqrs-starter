package ru.muravev.cqrs.es.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.es.exception.EventSerializationException;

@Component
public class JsonEventSerializer implements EventSerializer {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> String serialize(T event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new EventSerializationException(e);
        }
    }

    @Override
    public <T> T deserialize(String serializedEvent, Class<T> eventType) {
        try {
            return objectMapper.readValue(serializedEvent, eventType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
