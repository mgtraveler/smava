package com.zlotko.core;

import org.apache.commons.lang3.SystemUtils;

public enum Platform {
    WINDOWS, LINUX;

    public static String getOperatingSystem() {
        if (SystemUtils.IS_OS_LINUX) {
            return LINUX.name().toLowerCase();
        } else if (SystemUtils.IS_OS_WINDOWS) {
            return WINDOWS.name().toLowerCase();
        }
        throw new IllegalStateException("Operation system is not defined");
    }
}