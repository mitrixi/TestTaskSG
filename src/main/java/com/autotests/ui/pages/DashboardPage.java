package com.autotests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement playersButton = $(withText("Players online / total"));

    public void dashboardPageShouldBeVisible() {
        playersButton.shouldBe(Condition.visible);
    }

    public void clickPlayersButton() {
        playersButton.click();
    }
}