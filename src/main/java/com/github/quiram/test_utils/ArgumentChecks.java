package com.github.quiram.test_utils;

import java.util.List;
import java.util.function.Function;

import static com.github.quiram.test_utils.Exceptions.expectException;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ArgumentChecks {
    public static final List<String> BLANK_VALUES = asList(null, "", "    ");

    public static <T, R> void assertIllegalArguments(Function<T, R> function, String field, List<T> values) {
        values.forEach(value ->
                expectException(() -> function.apply(value), IllegalArgumentException.class,
                        field, format("Exception expected when testing value '%s' for field '%s;", value, field)
                ));
    }
}
