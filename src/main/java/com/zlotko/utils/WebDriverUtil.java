package com.zlotko.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.zlotko.core.HtmlCssAttr.VALUE;
import static com.zlotko.core.driver.WebDriverContainer.getWebDriver;
import static com.zlotko.core.driver.WebDriverContainer.hasWebDriverStarted;
import static com.zlotko.core.logger.Logger.LOGGER;
import static com.zlotko.core.props.Props.PROPS;
import static com.zlotko.utils.WaitUtil.fluentWait;
import static com.zlotko.utils.WaitUtil.waitForPageLoadCompleted;

public final class WebDriverUtil {

    private WebDriverUtil() {
    }

    public static void goToPage(String absoluteOrRelativeUrl) {
        String absoluteUrl = isAbsoluteUrl(absoluteOrRelativeUrl) ? absoluteOrRelativeUrl : PROPS.baseHost() + absoluteOrRelativeUrl;
        getWebDriver().navigate().to(absoluteUrl);
        waitForPageLoadCompleted();
        LOGGER.info("Url opened: {}", getWebDriver().getCurrentUrl());
    }

    @SuppressWarnings("unchecked")
    public static <T> T executeJavaScript(String jsCode, Object... arguments) {
        return (T) ((JavascriptExecutor) getWebDriver()).executeScript(jsCode, arguments);
    }

    public static void click(WebElement element) {
        fluentWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void type(WebElement element, String text) {
        fluentWait().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        fluentWait().until(ExpectedConditions.attributeToBe(element, VALUE, text));
    }

    public static void clearBrowserCache() {
        if (hasWebDriverStarted()) {
            getWebDriver().manage().deleteAllCookies();
            getWebDriver().navigate().refresh();
        }
    }

    public static void switchToFrame(WebElement element) {
        getWebDriver().switchTo().frame(element);
    }

    public static void selectOptionByValue(WebElement element, String optionValue) {
        Select select = new Select(element);
        select.selectByValue(optionValue);
    }

    public static void selectOptionByValue(WebElement element, int optionValue) {
        selectOptionByValue(element, String.valueOf(optionValue));
    }

    public static void selectComponentByIndex(WebElement element, int index) {
        click(element);
        click(getWebDriver().findElements(By.className("Select-option")).get(index));
    }

    private static boolean isAbsoluteUrl(String absoluteOrRelativeUrl) {
        return absoluteOrRelativeUrl.toLowerCase().startsWith("http:") || absoluteOrRelativeUrl.toLowerCase().startsWith("https:");
    }
}



