package ru.muravev.cqrs.component;

public interface Bus {

    /**
     * Публикация объекта в шину
     *
     * @param obj объект
     * @param <T> тип объекта
     */
    <T> void publish(T obj);
}
