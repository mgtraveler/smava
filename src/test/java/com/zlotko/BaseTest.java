package com.zlotko;

import com.zlotko.core.Platform;
import com.zlotko.core.junit.TestFailureExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.zlotko.core.driver.WebDriverContainer.quitAllWebDrivers;
import static com.zlotko.core.driver.WebDriverContainer.quitUnusedWebDrivers;
import static com.zlotko.core.props.Props.PROPS;
import static com.zlotko.utils.WebDriverUtil.clearBrowserCache;

@ExtendWith(TestFailureExtension.class)
public class BaseTest {

    static {
        System.setProperty("operatingSystem", Platform.getOperatingSystem());
        System.setProperty(PROPS.webDriverName(), PROPS.webDriverPath());
    }

    @BeforeEach
    public void setUp() {
        clearBrowserCache();
        quitUnusedWebDrivers();
    }

    @AfterAll
    public static void tearDown() {
        quitAllWebDrivers();
    }
}
