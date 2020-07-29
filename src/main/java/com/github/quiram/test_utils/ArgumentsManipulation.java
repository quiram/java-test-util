package com.github.quiram.test_utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Function;
import java.util.stream.IntStream;

public class ArgumentsManipulation {
    public static Function<Arguments, Arguments> extract(int i, int... indexes) {
        return args -> extract(args, i, indexes);
    }

    public static Arguments extract(Arguments arguments, int i, int... indexes) {
        final Object[] args = arguments.get();
        final Object[] newArgs = new Object[indexes.length + 1];
        newArgs[0] = args[i];
        IntStream.range(1, newArgs.length).forEach(j -> newArgs[j] = args[indexes[j - 1]]);

        return Arguments.of(newArgs);
    }

    @SafeVarargs
    public static Function<Arguments, Arguments> transform(Function<Object, Object> f, Function<Object, Object>... functions) {
        return args -> transform(args, f, functions);
    }

    @SafeVarargs
    public static Arguments transform(Arguments arguments, Function<Object, Object> f, Function<Object, Object>... functions) {
        if (arguments.get().length != functions.length + 1) {
            throw new IllegalArgumentException(String.format("Expected %d function(s), got %d", arguments.get().length, functions.length + 1));
        }

        final Object[] args = arguments.get();
        final Object[] newArgs = new Object[functions.length + 1];
        newArgs[0] = f.apply(args[0]);
        IntStream.range(1, newArgs.length).forEach(j -> newArgs[j] = functions[j - 1].apply(args[j]));

        return Arguments.of(newArgs);

    }
}
