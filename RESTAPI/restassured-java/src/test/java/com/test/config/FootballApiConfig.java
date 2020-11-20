package com.test.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class FootballApiConfig {

    public static RequestSpecification footballRequestSpec;
    public static ResponseSpecification footballResponseSpec;

    @BeforeClass
    public static void setUp() {
        footballRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .setBasePath("/v2/")
                .addHeader("X-Auth-Token", "e04874a9faae4e43a759bdcda2e166c3")
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        footballResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.responseSpecification = footballResponseSpec;
        RestAssured.requestSpecification = footballRequestSpec;
    }
}
