package com.zlotko.core.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.zlotko.core.logger.Logger.LOGGER;
import static java.lang.Thread.currentThread;
import static java.util.Arrays.asList;

public final class WebDriverFactory {

    private static List<AbstractWebDriverFactory> factories = asList(new ChromeWebDriverFactory());

    private WebDriverFactory() {
    }

    public static WebDriver createWebDriver() {
        LOGGER.info("New WebDriver is created for thread: {}", currentThread().getId());

        WebDriver webDriver = factories
                .stream()
                .filter(AbstractWebDriverFactory::isDefault)
                .findAny()
                .map(AbstractWebDriverFactory::create)
                .orElseThrow(() -> new IllegalArgumentException("WebDriver instance can not be created"));

        logBrowserVersion(webDriver);

        webDriver.manage().window().maximize();
        return webDriver;
    }

    private static void logBrowserVersion(WebDriver webdriver) {
        Capabilities capabilities = ((HasCapabilities) webdriver).getCapabilities();
        LOGGER.info("BrowserName={}, Version={}, Platform={}", capabilities.getBrowserName(),
                capabilities.getVersion(), capabilities.getPlatform());
    }
}
