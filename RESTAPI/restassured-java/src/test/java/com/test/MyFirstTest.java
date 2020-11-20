package com.test;

import com.test.config.VideoGameConfig;
import org.testng.annotations.Test;

import static com.test.config.VideoGameEndpoints.ALL_VIDEO_GAMES;
import static io.restassured.RestAssured.given;

public class MyFirstTest extends VideoGameConfig {

    @Test
    public void shouldAnswerWithTrue()
    {

        given()
                .log().all().
        when().
                get(ALL_VIDEO_GAMES).
        then()
                .log().all();
    }
}
