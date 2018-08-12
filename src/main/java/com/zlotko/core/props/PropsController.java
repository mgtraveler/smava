package com.zlotko.core.props;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:base.properties")
public interface PropsController extends Config {

    @Key("browser")
    public String browser();

    @Key("base.host")
    public String baseHost();

    @Key("explicit.wait")
    public long explicitWait();

    @Key("polling.wait")
    public long pollingWait();

    @Key("driver.path.${browser}.${operatingSystem}")
    String webDriverPath();

    @Key("driver.name.${browser}")
    String webDriverName();
}
