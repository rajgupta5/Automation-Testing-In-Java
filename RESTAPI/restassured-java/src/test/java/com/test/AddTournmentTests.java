package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddTournmentTests {


    public static RequestSpecification tournmentRequestSpec;
    public static ResponseSpecification tournmentResponseSpec;

    public String ADD_TOURNMENT = "addTournament";

    @BeforeClass
    public static void setUp() {
        tournmentRequestSpec = new RequestSpecBuilder()
                .setBaseUri("http://misc.zupee.in:3000")
                .setBasePath("/miniTournament/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        tournmentResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = tournmentRequestSpec;
        RestAssured.responseSpecification = tournmentResponseSpec;
    }

    @Test
    public void addTourmentVerification() {
        TournmentRequest newTournment = new TournmentRequest("Test", 100, 10, "2020-11-09T12:06", "2020-11-11T12:07");
        Response response = given().body(newTournment).when().post(ADD_TOURNMENT).then().extract().response();
        try {
            TournmentResponse tournmentResponse = new ObjectMapper().readValue(response.asString(), TournmentResponse.class);
            assertThat(tournmentResponse.getStatus(), equalTo("true"));
            assertThat(tournmentResponse.getResult().getName(), equalTo("Test"));
            assertThat(tournmentResponse.getResult().getReward(), equalTo("1000"));
            assertThat(tournmentResponse.getResult().getEntry(), equalTo("100"));
            assertThat(tournmentResponse.getResult().getNumQuestions(), equalTo("10"));
            assertThat(tournmentResponse.getResult().getStartTime(), equalTo("2020-11-09T12:06"));
            assertThat(tournmentResponse.getResult().getEndTime(), equalTo("2020-11-11T12:07"));
            assertThat(tournmentResponse.getResult().get_id(), notNullValue());

        } catch (JsonProcessingException e) {
            System.out.println("Error Reading Json Response" +e.getMessage());
        }

    }
}
