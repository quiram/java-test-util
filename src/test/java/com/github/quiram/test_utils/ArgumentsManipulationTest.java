package com.github.quiram.test_utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import static com.github.quiram.test_utils.ArgumentsManipulation.*;
import static com.github.quiram.utils.Random.randomInt;
import static com.github.quiram.utils.Random.randomString;
import static java.util.function.Function.identity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentsManipulationTest {
    @Test
    void canExtractTheOneElement() {
        final String value = randomString();
        final Arguments arguments = extract(Arguments.of(value), 0);
        final Object[] args = arguments.get();
        assertThat(args.length, is(1));
        assertThat(args[0], is(value));
    }

    @Test
    void canExtractSecondElement() {
        final String value = randomString();
        final Arguments arguments = extract(Arguments.of(randomString(), value), 1);
        final Object[] args = arguments.get();
        assertThat(args.length, is(1));
        assertThat(args[0], is(value));
    }

    @Test
    void canExtractSameContentInReverseOrder() {
        final String value1 = randomString();
        final String value2 = randomString();
        final Arguments arguments = extract(Arguments.of(value1, value2), 1, 0);
        final Object[] args = arguments.get();
        assertThat(args.length, is(2));
        assertThat(args[0], is(value2));
        assertThat(args[1], is(value1));
    }

    @Test
    void failToTransformIfMoreFunctionsThanArguments() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> transform(Arguments.of(randomString()), identity(), identity()));
        assertThat(exception.getMessage(), containsString("Expected 1 function(s), got 2"));
    }

    @Test
    void failToTransformIfFewerFunctionsThanArguments() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> transform(Arguments.of(randomString(), randomString()), identity()));
        assertThat(exception.getMessage(), containsString("Expected 2 function(s), got 1"));
    }

    @Test
    void functionsAreAppliedToArguments() {
        final String value1 = randomString();
        final int value2 = randomInt();
        final Arguments result = transform(Arguments.of(value1, value2), s -> s + "-modified", i -> (int) i + 1);
        final Object[] newArgs = result.get();
        assertThat(newArgs.length, is(2));
        assertThat(newArgs[0], is(value1 + "-modified"));
        assertThat(newArgs[1], is(value2 + 1));
    }

    @Test
    void failToFilterIfNumberOfFunctionsDoesNotMatchArguments() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> applyFilter(Arguments.of(randomString(), randomString()), arg -> true));
        assertThat(exception.getMessage(), containsString("Expected 2 function(s), got 1"));
    }

    @Test
    void returnTrueIfAllFiltersMatch() {
        assertTrue(applyFilter(Arguments.of(1), i -> true));
    }

    @Test
    void returnFalseIfNoFilterMatches() {
        assertFalse(applyFilter(Arguments.of(1), i -> false));
    }

    @Test
    void returnFalseIfAnyFilterDoesNotMatch() {
        assertFalse(applyFilter(Arguments.of(1, 2), i -> true, i -> false));
    }

    @Test
    void filterAppliesToArguments() {
        assertTrue(applyFilter(Arguments.of("hello", 2), s -> s.equals("hello"), i -> (int) i > 0));
    }
}