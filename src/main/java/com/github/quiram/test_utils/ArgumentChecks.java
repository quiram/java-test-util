package com.github.quiram.test_utils;

import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentChecks {
    public static final List<String> BLANK_VALUES = asList(null, "", "    ");

    public static <T, R> void assertIllegalArguments(Function<T, R> function, String field, List<T> values) {
        values.forEach(value ->
                {
                    final Exception exception = assertThrows(IllegalArgumentException.class, () -> function.apply(value), format("Exception expected when testing value '%s' for field '%s;", value, field));
                    assertThat(exception.getMessage(), containsString(field));
                }
        );
    }
}
