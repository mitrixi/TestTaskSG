package com.autotests.restapi.config;

import com.google.inject.AbstractModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class RestAssuredModule extends AbstractModule {
    @Override
    protected void configure() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://test-api.d6.dev.devcaz.com")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}