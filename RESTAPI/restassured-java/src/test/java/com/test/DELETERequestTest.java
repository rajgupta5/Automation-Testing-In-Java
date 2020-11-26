package com.test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DELETERequestTest {

    private static Logger LOG = LoggerFactory.getLogger(DELETERequestTest.class);

    @Test
    public void testDelete() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 1 : Create a new Student [POST]");
        Student body = new Student("NewUser1", "DeleteUser", "Female");
        Response response = RestAssured.given().header("accept", "application/json")
                .header("content-type", "application/json").body(body).post(url).andReturn();

        LOG.info("Created Student Record");
        response.getBody().prettyPrint();

        String id = response.getBody().jsonPath().getString("id");
        LOG.info("Get the created Student ID: " + id);

        LOG.info("Step - 2 : Delete the created record. [DELETE ]");
        String url1 = url + "/" + id;
        Response response1 = RestAssured.given().delete(url1).andReturn();

        LOG.info("Step - 3 : Print the response message and assert the status");
        LOG.info("Response Status Code: " + response1.getStatusCode());
        assertTrue(response1.getBody().prettyPrint().isEmpty());
        assertTrue(response1.getStatusCode()==204);
        LOG.info("Student with id: " +id+ " is deleted");
    }

}

