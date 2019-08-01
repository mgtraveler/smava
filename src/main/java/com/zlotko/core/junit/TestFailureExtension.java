package com.zlotko.core.junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static com.zlotko.utils.ReportUtil.takeScreenshotAndPageHtml;

public class TestFailureExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        takeScreenshotAndPageHtml();
        throw throwable;
    }
}
