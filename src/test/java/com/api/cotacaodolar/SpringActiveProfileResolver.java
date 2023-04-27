package com.api.cotacaodolar;

import org.springframework.test.context.ActiveProfilesResolver;

public class SpringActiveProfileResolver implements ActiveProfilesResolver {
    private String[] defaultActiveProfile = {"test"};

    @Override
    public String[] resolve(Class<?> aClass) {
        return defaultActiveProfile;
    }
}
