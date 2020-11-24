package com.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.AssertJUnit.assertEquals;

public class RestAssuredTutotiral {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.port = 443;
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("/events?id=390").then().statusCode(200).assertThat()
                .body("data.leagueId", equalTo(35));
    }

    @Test
    public void givenUrl_whenJsonResponseHasArrayWithGivenValuesUnderKey_thenCorrect() {
        get("/events?id=390").then().assertThat()
                .body("odds.price", hasItems("1.30", "5.25"));
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/users/eugenp").then().statusCode(200);
    }


    @Test
    public void whenRequestHead_thenOK() {
        when().request("HEAD", "/users/eugenp").then().statusCode(200);
    }

    @Test
    public void whenRequestedPost_thenCreated() {
        with().body(new Odd(5.25f, 1, 13.1f, "X"))
                .when()
                .request("POST", "/odds/new")
                .then()
                .statusCode(201);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {
        Response response = RestAssured.get("/users/eugenp");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("/users/eugenp").then().time(lessThan(5000L));
    }

    @Test
    public void givenUrl_whenXmlResponseValueTestsEqual_thenCorrect() {
        post("/employees").then().assertThat()
                .body("employees.employee.first-name", equalTo("Jane"));
    }

    @Test
    public void givenUrl_whenMultipleXmlValuesTestEqual_thenCorrect() {
        post("/employees").then().assertThat()
                .body("employees.employee.first-name", equalTo("Jane"))
                .body("employees.employee.last-name", equalTo("Daisy"))
                .body("employees.employee.sex", equalTo("f"));
    }

    @Test
    public void givenUrl_whenMultipleXmlValuesTestEqualInShortHand_thenCorrect() {
        post("/employees")
                .then().assertThat().body("employees.employee.first-name",
                equalTo("Jane"),"employees.employee.last-name",
                equalTo("Daisy"), "employees.employee.sex",
                equalTo("f"));
    }

    @Test
    public void givenUrl_whenValidatesXmlUsingXpath_thenCorrect() {
        post("/employees").then().assertThat().
                body(hasXPath("/employees/employee/first-name", containsString("Ja")));
    }

    @Test
    public void givenUrl_whenValidatesXmlUsingXpath2_thenCorrect() {
        post("/employees").then().assertThat()
                .body(hasXPath("/employees/employee/first-name[text()='Jane']"));
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponse_thenOK() {
        when().get("/repos/eugenp/tutorials")
                .then().log().body().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("/users/eugenp")
                .then().log().ifError();
        when().get("/users/eugenp")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("/users/eugenp")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("/users/eugenp")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }


}
