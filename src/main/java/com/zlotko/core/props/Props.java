package com.zlotko.core.props;

import org.aeonbits.owner.ConfigFactory;

public class Props {

    public static final PropsController PROPS = ConfigFactory.create(PropsController.class, System.getProperties());

    private Props() {
    }
}