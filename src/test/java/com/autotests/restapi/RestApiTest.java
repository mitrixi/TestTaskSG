package com.autotests.restapi;

import com.autotests.restapi.config.RestAssuredModule;
import com.autotests.restapi.models.PlayerModel;
import com.autotests.restapi.models.PlayerRegistrationModel;
import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Guice(modules = RestAssuredModule.class)
public class RestApiTest {
    @Inject
    ApiController apiController;

    private final String username = RandomStringUtils.randomAlphabetic(6);
    private final String password = "Password8=";
    private final String email = username + "@gmail.com";
    private String token;
    private PlayerModel testUser;

    private final PlayerRegistrationModel playerRegistrationModel = PlayerRegistrationModel.builder()
            .username(username)
            .password_change(password)
            .password_repeat(password)
            .email(email)
            .build();

    @Test
    public void checkTokenReturn() {
        token = apiController.getGuestAccessToken();
        assertNotNull(token);
    }

    @Test(dependsOnMethods = "checkTokenReturn")
    public void checkRegistrationSuccessful() {
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
        testUser = apiController.getPlayerProfileData(token, testUser.getId(), 200);
        assertEquals(testUser.getUsername(), username);
        assertEquals(testUser.getEmail(), email);
    }

    @Test(dependsOnMethods = "checkPlayerProfileData")
    public void checkProfileDataOfOtherPlayer() {
        testUser = apiController.getPlayerProfileData(token, testUser.getId() + 1, 404);
        assertNull(testUser.getId());
    }
}