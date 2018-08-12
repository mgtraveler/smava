package com.zlotko.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.zlotko.core.driver.WebDriverFactory.CHROME;
import static com.zlotko.core.logger.Logger.LOGGER;
import static com.zlotko.core.props.Props.PROPS;

public class ChromeWebDriverFactory extends AbstractWebDriverFactory {

    @Override
    WebDriver create() {
        ChromeOptions options = createChromeOptions();
        return new ChromeDriver(options);
    }

    @Override
    boolean isDefault() {
        return CHROME.equalsIgnoreCase(PROPS.browser());
    }

    ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.merge(createCommonCapabilities());
        LOGGER.info("Chrome options: {}", options.toString());
        return options;
    }
}