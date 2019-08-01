package com.zlotko.utils;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.zlotko.core.driver.WebDriverContainer.getWebDriver;
import static com.zlotko.core.driver.WebDriverContainer.hasWebDriverStarted;
import static com.zlotko.core.logger.Logger.LOGGER;

public final class ReportUtil {

    private ReportUtil() {
    }

    public static void takeScreenshotAndPageHtml() {
        if (hasWebDriverStarted()) {
            File screenShotFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            pageHtml(getWebDriver().getPageSource().getBytes());
            try {
                screenshot(Files.toByteArray(screenShotFile));
            } catch (IOException e) {
                LOGGER.error("Failed to read File to byte[]", e);
            }
        }
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] screenShotData) {
        return screenShotData;
    }

    @Attachment(type = "text/html")
    public static byte[] pageHtml(byte[] pageSourceData) {
        return pageSourceData;
    }
}