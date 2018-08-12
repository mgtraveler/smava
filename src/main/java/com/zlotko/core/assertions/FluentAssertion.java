package com.zlotko.core.assertions;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import static com.zlotko.utils.WaitUtil.fluentWait;

public class FluentAssertion {

    public static void fluentAssert(Runnable assertion) {
        try {
            fluentWait()
                    .ignoring(AssertionError.class)
                    .until((WebDriver driver) -> {
                        assertion.run();
                        return true;
                    });
        } catch (TimeoutException e) {
            throw new AssertionError(String.format("Timeout expired: %s", e.getCause().getMessage()), e);
        }
    }
}
