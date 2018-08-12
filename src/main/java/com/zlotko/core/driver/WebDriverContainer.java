package com.zlotko.core.driver;

import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.zlotko.core.logger.Logger.LOGGER;
import static java.lang.Thread.currentThread;

public final class WebDriverContainer {

    private static Map<Long, WebDriver> WEB_DRIVERS_BY_THREAD = new ConcurrentHashMap<>(4);
    private static Collection<Thread> ALL_WEB_DRIVERS_THREADS = new ConcurrentLinkedQueue<>();

    private WebDriverContainer() {
    }

    public static WebDriver getWebDriver() {
        WebDriver webDriver = WEB_DRIVERS_BY_THREAD.get(currentThread().getId());
        if (webDriver != null) {
            return webDriver;
        }
        LOGGER.info("New WebDriver is created for thread: {}", currentThread().getId());
        return setWebDriver(WebDriverFactory.createWebDriver());
    }

    public static void closeWebDriver() {
        closeWebDriver(currentThread());
    }

    public static void closeUnusedWebDrivers() {
        for (Thread thread : ALL_WEB_DRIVERS_THREADS) {
            if (!thread.isAlive()) {
                closeWebDriver(thread);
            }
        }
    }

    public static boolean hasWebDriverStarted() {
        return WEB_DRIVERS_BY_THREAD.containsKey(currentThread().getId());
    }

    private static WebDriver setWebDriver(WebDriver webDriver) {
        WEB_DRIVERS_BY_THREAD.put(currentThread().getId(), webDriver);
        ALL_WEB_DRIVERS_THREADS.add(currentThread());
        return webDriver;
    }

    private static void closeWebDriver(Thread thread) {
        long threadId = thread.getId();
        ALL_WEB_DRIVERS_THREADS.remove(thread);
        WebDriver webdriver = WEB_DRIVERS_BY_THREAD.remove(threadId);

        if (webdriver != null) {
            LOGGER.info("Close {} for thread: {}.", webdriver, threadId);
            webdriver.quit();
        }
    }
}
