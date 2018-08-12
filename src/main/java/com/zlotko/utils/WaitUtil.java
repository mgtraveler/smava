package com.zlotko.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static com.zlotko.core.driver.WebDriverContainer.getWebDriver;
import static com.zlotko.core.props.Props.PROPS;
import static com.zlotko.utils.WebDriverUtil.executeJavaScript;

public final class WaitUtil {

    private WaitUtil() {
    }

    public static FluentWait<WebDriver> fluentWait() {
        return fluentWait(Duration.ofSeconds(PROPS.explicitWait()), Duration.ofMillis(PROPS.pollingWait()));
    }

    public static FluentWait<WebDriver> fluentWait(Duration explicitTimeout, Duration pollingTimeout) {
        return new FluentWait<>(getWebDriver())
                .withTimeout(explicitTimeout)
                .pollingEvery(pollingTimeout)
                .ignoring(TimeoutException.class);
    }

    public static void waitForPageLoadCompleted() {
        fluentWait().until(webDriver -> executeJavaScript("return document.readyState == 'complete'"));
    }
}
