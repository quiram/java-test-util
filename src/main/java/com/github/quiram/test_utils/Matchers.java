package com.github.quiram.test_utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {
    public static Matcher<Object> containsString(String substring) {
        return new ToStringContainsString(substring);
    }


    private static class ToStringContainsString extends TypeSafeMatcher<Object> {
        private final String substring;

        ToStringContainsString(String substring) {
            this.substring = substring;
        }

        @Override
        protected boolean matchesSafely(Object item) {
            return item.toString().contains(substring);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("an object whose ToString representation contains ")
                    .appendValue(substring);
        }
    }
}
