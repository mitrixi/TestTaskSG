package com.autotests.restapi;

import com.google.inject.Inject;
import com.autotests.restapi.config.RestAssuredModule;
import com.autotests.restapi.models.PlayerModel;
import com.autotests.restapi.models.PlayerRegistrationModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Guice(modules = RestAssuredModule.class)
public class RestApiTest {
    @Inject
    ApiController apiController;

    private final String username = RandomStringUtils.randomAlphabetic(6);
    private final String password = "Password8=";
    private final String email = username + "@gmail.com";
    private String token;
    private PlayerModel testUser;

    @Test
    public void checkTokenReturn() {
        token = apiController.getGuestAccessToken();
        assertNotNull(token);
    }

    @Test(dependsOnMethods = "checkTokenReturn")
    public void checkRegistrationSuccessful() {
        PlayerRegistrationModel playerRegistrationModel = PlayerRegistrationModel.builder()
                .username(username)
                .password_change(password)
                .password_repeat(password)
                .email(email)
                .build();

        testUser = apiController.registerNewPlayer(token, playerRegistrationModel);
        assertEquals(testUser.getUsername(), username);
        assertEquals(testUser.getEmail(), email);
    }

    @Test(dependsOnMethods = "checkRegistrationSuccessful")
    public void checkAuthorizationSuccessful() {
        token = apiController.getPlayerAccessToken(username, password);
        assertNotNull(token);
    }

    @Test(dependsOnMethods = "checkAuthorizationSuccessful")
    public void checkPlayerProfileData() {
        apiController.getPlayerProfileData(token, testUser.getId(), 200);
    }

    @Test(dependsOnMethods = "checkPlayerProfileData")
    public void checkProfileDataOfOtherPlayer() {
        apiController.getPlayerProfileData(token, testUser.getId() + 1, 404);
    }
}
