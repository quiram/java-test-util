package com.github.quiram.test_utils;

import com.github.quiram.utils.VoidCallable;

import java.util.concurrent.Callable;

import static java.lang.String.format;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class Exceptions {
    public static <T> void expectException(Callable<T> statement, Class<? extends Exception> expectedException,
                                            String expectedMessage, String failureMessage) {
        try {
            statement.call();
            fail(failureMessage);
        } catch (Exception e) {
            assertEquals(expectedException, e.getClass());
            assertThat(e.getMessage(), containsString(expectedMessage));
        }
    }

    @SuppressWarnings("unchecked")
    public static <E extends Exception> E expectException(Callable<?> statement, Class<E> expectedException) {
        try {
            statement.call();
            fail(format("Expected exception %s not thrown", expectedException.getSimpleName()));
            return null; // Just so the compiler doesn't complain, we'll never get to this point
        } catch (Exception e) {
            assertEquals(expectedException, e.getClass());
            return (E) e;
        }
    }

    public static void expectException(VoidCallable statement, Class<? extends Exception> expectedException,
                                       String expectedMessage, String failureMessage) {
        try {
            statement.call();
            fail(failureMessage);
        } catch (Exception e) {
            assertEquals(expectedException, e.getClass());
            assertThat(e.getMessage(), containsString(expectedMessage));
        }
    }

    @SuppressWarnings("unchecked")
    public static <E extends Exception> E expectException(VoidCallable statement, Class<E> expectedException) {
        try {
            statement.call();
            fail(format("Expected exception %s not thrown", expectedException.getSimpleName()));
            return null; // Just so the compiler doesn't complain, we'll never get to this point
        } catch (Exception e) {
            assertEquals(expectedException, e.getClass());
            return (E) e;
        }
    }
}
