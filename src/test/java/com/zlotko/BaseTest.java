package com.zlotko;

import com.zlotko.core.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import static com.zlotko.core.driver.WebDriverContainer.closeUnusedWebDrivers;
import static com.zlotko.core.driver.WebDriverContainer.closeWebDriver;
import static com.zlotko.core.props.Props.PROPS;
import static com.zlotko.utils.WebDriverUtil.clearBrowserCache;

public class BaseTest {

    static {
        System.setProperty("operatingSystem", Platform.getOperatingSystem());
        System.setProperty(PROPS.webDriverName(), PROPS.webDriverPath());
    }

    @BeforeEach
    public void setUp() {
        clearBrowserCache();
        closeUnusedWebDrivers();
    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver();
    }
}
