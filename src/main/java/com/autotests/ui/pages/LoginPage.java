package com.autotests.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginInputField = $(byId("UserLogin_username"));
    private final SelenideElement passwordInputField = $(byId("UserLogin_password"));
    private final SelenideElement signInButton = $(byName("yt0"));

    public void enterLoginIntoInputField(String login) {
        loginInputField.sendKeys(login);
    }

    public void enterPasswordIntoInputField(String password) {
        passwordInputField.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }
}