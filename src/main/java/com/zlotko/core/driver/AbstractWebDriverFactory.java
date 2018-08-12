package com.zlotko.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class AbstractWebDriverFactory {

    abstract WebDriver create();

    abstract boolean isDefault();

    DesiredCapabilities createCommonCapabilities() {
        return new DesiredCapabilities();
    }
}
