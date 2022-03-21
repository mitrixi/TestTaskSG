package com.autotests.ui.steps;

import com.google.inject.Inject;
import com.autotests.ui.pages.PlayerManagementPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.autotests.ui.config.TablePlayersColumnNames.REGISTRATION_DATE;
import static org.testng.Assert.assertTrue;

public class SortPlayersListStepDefinitions {
    @Inject
    PlayerManagementPage playerManagementPage;

    @Given("players management page is open")
    public void playersManagementPageIsOpen() {
        playerManagementPage.playerManagementPageShouldBeVisible();
    }

    @When("sort players table by particular column")
    public void sortPlayersTableByParticularColumn() {
        playerManagementPage.sortPlayersByParticularColumn(REGISTRATION_DATE);
    }

    @Then("players table has sorted")
    public void playersTableHasSorted() {
        assertTrue(playerManagementPage.isPlayersHasSorted());
    }
}
