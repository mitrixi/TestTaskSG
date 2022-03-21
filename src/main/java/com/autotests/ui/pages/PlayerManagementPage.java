package com.autotests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class PlayerManagementPage {
    private final SelenideElement pageContentTitle = $(byTagName("strong"));
    private ElementsCollection playersRegistrationDateList;

    public void playerManagementPageShouldBeVisible() {
        pageContentTitle.shouldHave(Condition.text("Player management"));
    }

    public void sortPlayersByParticularColumn(String columnName) {
        $(withText(columnName)).click();
        sleep(3000);    // time to animate the change of results
    }

    @SneakyThrows
    public boolean isPlayersHasSorted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> datesList = new ArrayList<>(20);
        playersRegistrationDateList = $(byTagName("tbody")).$$(byXpath("./tr/td[10]"));

        for (SelenideElement selenideElement : playersRegistrationDateList) {
            datesList.add(dateFormat.parse(selenideElement.getText()));
        }

        boolean isSorted = true;
        for (int i = 1; i < datesList.size(); i++) {
            if (datesList.get(i).before(datesList.get(i - 1))) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}