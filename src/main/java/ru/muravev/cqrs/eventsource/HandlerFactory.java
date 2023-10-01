package ru.muravev.cqrs.eventsource;

import java.lang.reflect.Method;
import java.util.function.Function;

public interface HandlerFactory {
    Handler createHandlerOnMethod(Method method);

    Getter createGetterOnMethod(Method method);

}
