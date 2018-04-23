package com.github.quiram.test_utils;

import org.mockito.verification.VerificationMode;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MockitoVerifications {

    public static VerificationMode once() {
        return times(1);
    }

    public static VerificationMode twice() {
        return times(2);
    }

    public static VerificationMode thrice() {
        return times(3);
    }
}
