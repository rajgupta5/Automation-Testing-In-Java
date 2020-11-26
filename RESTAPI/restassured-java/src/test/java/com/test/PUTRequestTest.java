package com.test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PUTRequestTest {

    private static Logger LOG = LoggerFactory.getLogger(PUTRequestTest.class);

    @Test
    public void testPUTusingPOJO() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 1 : Create a new Student [POST]");

        Student body = new Student("Ryan", "Jackson", "Male");
        Response response = RestAssured.given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(body)
                .post(url)
                .andReturn();

        LOG.info("Created Student Record");
        response.getBody().prettyPrint();

        String id = response.getBody().jsonPath().getString("id");
        LOG.info("Get the created Student ID: " + id);

        LOG.info("Step - 2 : Update Student's record [PUT]");
        Student bodyUpdate = new Student("John", "LP", "Male");
        bodyUpdate.id = Long.parseLong(id);
        String url1 = url + "/" + id;
        Response response1 = RestAssured.given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(bodyUpdate)
                .put(url1)
                .andReturn();

        LOG.info("Step - 3 : Print the response message and assert the status");
        response1.getBody().prettyPrint();
        LOG.info("Status " + response.getStatusCode());
        assertTrue(response.getStatusCode() == 201);

    }
}
