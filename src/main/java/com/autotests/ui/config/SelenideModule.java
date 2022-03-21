package com.autotests.ui.config;

import com.codeborne.selenide.Configuration;
import com.google.inject.AbstractModule;

public class SelenideModule extends AbstractModule {
    @Override
    protected void configure() {
        Configuration.baseUrl = "http://test-app.d6.dev.devcaz.com";
    }
}