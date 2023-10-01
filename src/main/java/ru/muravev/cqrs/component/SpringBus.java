package ru.muravev.cqrs.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringBus implements Bus {
    private final ApplicationEventPublisher publisher;


    @Override
    public <T> void publish(T obj) {
        publisher.publishEvent(obj);
    }
}
