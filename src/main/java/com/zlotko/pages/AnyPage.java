package com.zlotko.pages;

import com.zlotko.core.annotations.DefaultUrl;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;

import static com.zlotko.core.driver.WebDriverContainer.getWebDriver;
import static com.zlotko.utils.WebDriverUtil.goToPage;

public class AnyPage {

    protected AnyPage() {
        PageFactory.initElements(getWebDriver(), this);
    }

    public void open() {
        String defaultUrl = getDeclaredDefaultUrl();
        goToPage(defaultUrl);
    }

    public boolean isOnPage() {
        return getWebDriver().getCurrentUrl().contains(getDeclaredDefaultUrl());
    }

    private String getDeclaredDefaultUrl() {
        DefaultUrl urlAnnotation = this.getClass().getAnnotation(DefaultUrl.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        } else {
            return StringUtils.EMPTY;
        }
    }
}