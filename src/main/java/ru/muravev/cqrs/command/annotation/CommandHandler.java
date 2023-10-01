package ru.muravev.cqrs.command.annotation;

import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Обработчик комманд
 * Метод помеченный данной аннотацией может принимать как объект комманды, так и AggregateCommand
 *
 * @see ru.muravev.cqrs.command.AggregateCommand
 */
@EventListener
@Transactional
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandHandler {
}
