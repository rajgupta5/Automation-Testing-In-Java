package com.test;

import com.test.config.FootballApiConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getAreas() {
        given().queryParams("area", 2072).
                when().get("areas/").
                then();
    }

    @Test
    public void getDateFounded() {
        given().
                when().get("teams/57").
                then().body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName() {
        given().
                when().get("competitions/2021/teams").
                then().body("teams.name[0]", equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamsData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);

    }

    @Test
    public void getAllTeamsData_CheckFirst() {

        Response response =   given().
                when().get("teams/57").
                then().contentType(ContentType.JSON).extract().response();
        System.out.println(response.asString());

    }

    @Test
    public void getAllTeamsHeaders() {

        Response response =   given().
                when().get("teams/57").
                then().contentType(ContentType.JSON).extract().response();

        Headers headers = response.getHeaders();
        System.out.println(headers);

    }

    @Test
    public void getFirstName() {
        String firstName = given().
                when().get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
        System.out.println(firstName);
    }

    @Test
    public void getAllNames() {
        Response response = given().
                when().get("competitions/2021/teams").then().extract().response();
        ArrayList<String> names = response.path("teams.name");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
