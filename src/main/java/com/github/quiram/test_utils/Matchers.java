package com.github.quiram.test_utils;

import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.CoreMatchers.is;

public class Matchers {
    public static Matcher<Object> containsString(String substring) {
        return new ToStringContainsString(substring);
    }

    public static <T> Matcher<T> are(T object) {
        return is(object);
    }

    public static <T> Matcher<T> are(Matcher<T> matcher) {
        return is(matcher);
    }

    public static <T> Matcher<T> that(Matcher<T> matcher) {
        return matcher;
    }

    public static <T> Matcher<Pair<? extends T, ? extends T>> both(Matcher<T> matcher) {
        return new PairMatcher<>(matcher);
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

    private static class PairMatcher<T> extends TypeSafeMatcher<Pair<? extends T, ? extends T>> {

        private final Matcher<T> matcher;

        PairMatcher(Matcher<T> matcher) {
            this.matcher = matcher;
        }

        @Override
        protected boolean matchesSafely(Pair<? extends T, ? extends T> item) {
            return matcher.matches(item.getLeft()) && matcher.matches(item.getRight());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a pair of elements where both elements ").appendDescriptionOf(matcher);
        }
    }
}
