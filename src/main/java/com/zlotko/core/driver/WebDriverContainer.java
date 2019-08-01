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

        if (webDriver == null) {
            webDriver = WebDriverFactory.createWebDriver();
            setWebDriver(webDriver);
        }
        return webDriver;
    }

    public static void quitAllWebDrivers() {
        ALL_WEB_DRIVERS_THREADS.forEach(WebDriverContainer::quitWebDriver);
    }

    public static void quitUnusedWebDrivers() {
        ALL_WEB_DRIVERS_THREADS.forEach(thread -> {
            if (!thread.isAlive()) {
                quitWebDriver(thread);
            }
        });
    }

    public static boolean hasWebDriverStarted() {
        return WEB_DRIVERS_BY_THREAD.containsKey(currentThread().getId());
    }

    private static void quitWebDriver(Thread thread) {
        long threadId = thread.getId();
        ALL_WEB_DRIVERS_THREADS.remove(thread);
        WebDriver webdriver = WEB_DRIVERS_BY_THREAD.remove(threadId);

        if (webdriver != null) {
            LOGGER.info("Close {} for thread: {}.", webdriver, threadId);
            webdriver.quit();
        }
    }

    private static void setWebDriver(WebDriver webDriver) {
        WEB_DRIVERS_BY_THREAD.put(currentThread().getId(), webDriver);
        ALL_WEB_DRIVERS_THREADS.add(currentThread());
    }
}
