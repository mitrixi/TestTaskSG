package com.autotests.ui.steps;

import com.google.inject.Inject;
import com.autotests.ui.pages.DashboardPage;
import com.autotests.ui.pages.PlayerManagementPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OpenPlayersListStepDefinitions {
    @Inject
    DashboardPage dashboardPage;
    @Inject
    PlayerManagementPage playerManagementPage;

    @Given("dashboard page is open")
    public void dashboardPageIsOpen() {
        dashboardPage.dashboardPageShouldBeVisible();
    }

    @When("go to players management page")
    public void goToPlayersManagementPage() {
        dashboardPage.clickPlayersButton();
    }

    @Then("players management page has loaded")
    public void playersManagementPageHasLoaded() {
        playerManagementPage.playerManagementPageShouldBeVisible();
    }
}
