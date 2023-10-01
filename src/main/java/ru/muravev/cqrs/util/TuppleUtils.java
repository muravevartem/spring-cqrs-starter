package ru.muravev.cqrs.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@UtilityClass
public class TuppleUtils {
    public static <T> T firstNotNull(T ... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public static String firstNotEmpty(String ... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .findFirst()
                .orElse(null);
    }
}
