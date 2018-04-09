package com.amarinperez.test_utils;

import java.util.concurrent.Callable;

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
}
