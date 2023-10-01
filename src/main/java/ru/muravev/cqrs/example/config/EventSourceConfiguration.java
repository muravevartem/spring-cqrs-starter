package ru.muravev.cqrs.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.muravev.cqrs.es.customizer.EventTypeCustomizer;

import static ru.muravev.cqrs.es.customizer.EventTypeCustomizer.ofPackages;

@Configuration
public class EventSourceConfiguration {

    @Bean
    public EventTypeCustomizer eventSubtypeCustomizer() {
        return ofPackages(
                "ru.muravev.cqrs.example.event"
        );
    }

}
