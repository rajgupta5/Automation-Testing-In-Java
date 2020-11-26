package com.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class POSTRequestTest {

    private static Logger LOG = LoggerFactory.getLogger(POSTRequestTest.class);

    @Test
    public void testPOSTStringBody() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 1 : Target resource ( server ) : " + url);

        String body = "{\"first_name\": \"Jack\", \"last_name\": \"Preacher\", \"gender\": \"Male\" }";
        LOG.info("Step - 2 : Message body: " + body);

        LOG.info("Step - 3 : Send a POST Request");
        Response response = RestAssured.given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(body)
                .post(url)
                .andReturn();

        LOG.info("Step - 4 : Print the response message and assert the status response code is 201 - Created");
        response.getBody().prettyPrint();
        assertTrue(response.getStatusCode() == 201);

    }

    @Test
    public void testPOSTusingPOJO() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 1 : Target resource ( server ) : " + url);

        Student body = new Student("Katherine", "AK", "Female");
        LOG.info("Step - 2 : Message body: " + body);

        LOG.info("Step - 3 : Send a POST Request");
        Response response = RestAssured.given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(body)
                .post(url)
                .andReturn();

        LOG.info("Step - 4 : Print the response message and assert the status response code is 201 - Created");
        response.getBody().prettyPrint();
        assertTrue(response.getStatusCode() == 201);

    }
}

//
//class Student {
//
//    public Student(String firstName, String lastName, String gender) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.gender = gender;
//    }
//
//    @JsonProperty("id")
//    Long id;
//
//    @JsonProperty("first_name")
//    String firstName;
//
//    @JsonProperty("last_name")
//    String lastName;
//
//    @JsonProperty("gender")
//    String gender;
//}
