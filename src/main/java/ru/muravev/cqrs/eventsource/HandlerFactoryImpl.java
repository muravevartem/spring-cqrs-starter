package ru.muravev.cqrs.eventsource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.invoke.*;
import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandlerFactoryImpl implements HandlerFactory {


    @Override
    public Handler createHandlerOnMethod(Method method) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(
                    method.getDeclaringClass(),
                    MethodHandles.lookup()
            );
            return createHandlerByMethodHandle(lookup, lookup.unreflect(method));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Getter createGetterOnMethod(Method method) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(
                    method.getDeclaringClass(),
                    MethodHandles.lookup()
            );
            return createGetterByMethodHandle(lookup, lookup.unreflect(method));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Getter createGetterByMethodHandle(MethodHandles.Lookup lookup, MethodHandle methodHandle) {
        try {
            CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "get",
                    MethodType.methodType(Getter.class),
                    MethodType.methodType(Object.class, Object.class),
                    methodHandle,
                    methodHandle.type()
            );
            return (Getter) callSite.getTarget().invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Handler createHandlerByMethodHandle(MethodHandles.Lookup lookup, MethodHandle methodHandle) {
        try {
            CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "handle",
                    MethodType.methodType(Handler.class),
                    MethodType.methodType(void.class, Object.class, Object.class),
                    methodHandle,
                    methodHandle.type()
            );
            return (Handler) callSite.getTarget().invokeExact();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }
}
