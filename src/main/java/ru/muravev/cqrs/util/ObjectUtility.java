package ru.muravev.cqrs.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

@UtilityClass
public class ObjectUtility {

    @SafeVarargs
    public static <T> Optional<T> getFirstNotNull(T ... objects) {
        return Arrays.stream(objects)
                .filter(Objects::nonNull)
                .findFirst();
    }

    public static Optional<String> getFirstNotNull(String ... strings) {
        return Arrays.stream(strings)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .findFirst();
    }
}
