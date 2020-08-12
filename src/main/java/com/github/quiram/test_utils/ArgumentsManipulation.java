package com.github.quiram.test_utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Function;
import java.util.stream.IntStream;

public class ArgumentsManipulation {
    public static Function<Arguments, Arguments> extract(int... indexes) {
        return args -> extract(args, indexes);
    }

    public static Arguments extract(Arguments arguments, int... indexes) {
        final Object[] args = arguments.get();
        final Object[] newArgs = new Object[indexes.length];
        IntStream.range(0, newArgs.length).forEach(j -> newArgs[j] = args[indexes[j]]);

        return Arguments.of(newArgs);
    }

    @SafeVarargs
    public static Function<Arguments, Arguments> transform(Function<Object, Object>... functions) {
        return args -> transform(args, functions);
    }

    @SafeVarargs
    public static Arguments transform(Arguments arguments, Function<Object, Object>... functions) {
        if (arguments.get().length != functions.length) {
            throw new IllegalArgumentException(String.format("Expected %d function(s), got %d", arguments.get().length, functions.length));
        }

        final Object[] args = arguments.get();
        final Object[] newArgs = new Object[functions.length];
        IntStream.range(0, newArgs.length).forEach(j -> newArgs[j] = functions[j].apply(args[j]));

        return Arguments.of(newArgs);

    }
}
