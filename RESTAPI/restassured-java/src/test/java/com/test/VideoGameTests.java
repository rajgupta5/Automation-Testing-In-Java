package com.test;

import com.test.config.VideoGameConfig;
import org.testng.annotations.Test;

import static com.test.config.VideoGameEndpoints.ALL_VIDEO_GAMES;
import static com.test.config.VideoGameEndpoints.SINGLE_VIDEO_GAME;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameTests extends VideoGameConfig {


    @Test
    public void getAllGames() {
        given().
        when().get(ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void createNewGameByJSON() {
        String gameBody = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"string\",\n" +
                "  \"releaseDate\": \"2020-10-26T17:37:31.681Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"string\",\n" +
                "  \"rating\": \"string\"\n" +
                "}";

        given().body(gameBody).
        when().post(ALL_VIDEO_GAMES).then();
    }


    @Test
    public void createNewGameByXML() {
        String gameBodyXML = "<videoGame category=\"string\" rating=\"string\">\n" +
                "    <id>12</id>\n" +
                "    <name>string</name>\n" +
                "    <releaseDate>2020-10-26T00:00:00+05:30</releaseDate>\n" +
                "    <reviewScore>0</reviewScore>\n" +
                "  </videoGame>";

        given().
                body(gameBodyXML).
        when().
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
                post(ALL_VIDEO_GAMES).
        then();
    }


    @Test
    public void getSingleVideoGame() {
        given().pathParam("videoGameId", "5").when().get(SINGLE_VIDEO_GAME).then();
    }

    @Test
    public void jsonSerialization() {
        VideoGame videoGame = new VideoGame("99", "2020-10-26T00:00:00+05:30", "awesomegame", "3", 15, "Shooter");
        given().body(videoGame).when().post(ALL_VIDEO_GAMES).then();
    }

    @Test
    public void testVideoGameJsonSchema() {
        given().pathParam("videoGameId", 5).when().get(SINGLE_VIDEO_GAME).then().body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void testPOJOResponse() {
        VideoGame videoGame = given().pathParam("videoGameId", 5).when().get(SINGLE_VIDEO_GAME).then().extract().response().getBody().as(VideoGame.class);
        System.out.println(videoGame);
    }


    @Test
    public void captureResponseTime() {
        long time = get(ALL_VIDEO_GAMES).time();
        System.out.println(time);
    }

    @Test
    public void assertResponseTime() {
        when().get(ALL_VIDEO_GAMES).then().time(lessThan(1000L));

    }
}
