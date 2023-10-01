package ru.muravev.cqrs.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;

@UtilityClass
public class MethodUtils {

    public static Class<?> getFirstParameterType(Method method) {
        int parameterCount = method.getParameterCount();
        if (parameterCount != 1)
            throw new IllegalArgumentException();
        return method.getParameterTypes()[0];
    }
}
