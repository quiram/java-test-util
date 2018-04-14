package com.amarinperez.test_utils;

import java.util.List;
import java.util.function.Function;

import static com.amarinperez.test_utils.Exceptions.expectException;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class Constructors {
    public static final List<String> BLANK_VALUES = asList(null, "", "    ");

    public static <T, R> void assertIllegalArguments(Function<T, R> constructor, String field, List<T> values) {
        values.forEach(value ->
                expectException(() -> constructor.apply(value), IllegalArgumentException.class,
                        field, format("Exception expected when testing value '%s' for field '%s;", value, field)
                ));
    }
}
