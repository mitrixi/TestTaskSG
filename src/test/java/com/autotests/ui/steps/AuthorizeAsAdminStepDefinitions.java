package com.autotests.ui.steps;

import com.google.inject.Inject;
import com.autotests.TestData;
import com.autotests.ui.pages.DashboardPage;
import com.autotests.ui.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizeAsAdminStepDefinitions {
    @Inject
    LoginPage loginPage;
    @Inject
    DashboardPage dashboardPage;

    @Given("login page is open")
    public void loginPageIsOpen() {
        open("http://test-app.d6.dev.devcaz.com/admin/login");
    }

    @When("admin credentials are entered")
    public void adminCredentialsAreEntered() {
        loginPage.enterLoginIntoInputField(TestData.ADMIN_LOGIN);
        loginPage.enterPasswordIntoInputField(TestData.ADMIN_PASSWORD);
    }

    @And("admin credentials submitted")
    public void adminCredentialsSubmitted() {
        loginPage.clickSignInButton();
    }

    @Then("dashboard page has loaded")
    public void dashboardPageHasLoaded() {
        dashboardPage.dashboardPageShouldBeVisible();
    }
}
