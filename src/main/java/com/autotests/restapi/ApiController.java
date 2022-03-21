package com.autotests.restapi;

import com.autotests.TestData;
import com.autotests.restapi.config.EndPoints;
import com.autotests.restapi.config.GrantType;
import com.autotests.restapi.config.Scope;
import com.autotests.restapi.config.ScopeName;
import com.autotests.restapi.models.AuthModel;
import com.autotests.restapi.models.PlayerModel;
import com.autotests.restapi.models.PlayerRegistrationModel;

import static io.restassured.RestAssured.given;

public class ApiController {

    public String getGuestAccessToken() {
        AuthModel body = new AuthModel()
                .setGrant_type(GrantType.CLIENT_CREDENTIALS)
                .setScope(Scope.GUEST + ScopeName.DEFAULT);

        return given()
                .auth().preemptive().basic(
                        TestData.BASIC_AUTHENTICATION_USERNAME,
                        TestData.BASIC_AUTHENTICATION_PASSWORD)
                .body(body)
                .post(EndPoints.TOKEN)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("access_token");
    }

    public PlayerModel registerNewPlayer(String token, PlayerRegistrationModel playerRegistrationModel) {
        return given()
                .header("Authorization", "Bearer " + token)
                .body(playerRegistrationModel)
                .post(EndPoints.PLAYERS)
                .then()
                .statusCode(201)
                .extract().body().as(PlayerModel.class);
    }

    public String getPlayerAccessToken(String username, String password) {
        AuthModel body = new AuthModel()
                .setGrant_type(GrantType.PASSWORD)
                .setUsername(username)
                .setPassword(password);

        return given()
                .auth().preemptive().basic(
                        TestData.BASIC_AUTHENTICATION_USERNAME,
                        TestData.BASIC_AUTHENTICATION_PASSWORD)
                .body(body)
                .post(EndPoints.TOKEN)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("access_token");
    }

    public PlayerModel getPlayerProfileData(String token, Integer playerId, int expectedStatusCode) {
        return given()
                .header("Authorization", "Bearer " + token)
                .pathParam("playerId", playerId)
                .get(EndPoints.PLAYERS + "/{playerId}")
                .then()
                .statusCode(expectedStatusCode)
                .extract().body().as(PlayerModel.class);
    }
}