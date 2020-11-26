package com.test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class GETRequestTest {

    private static Logger LOG = LoggerFactory.getLogger(GETRequestTest.class);

    @Test
    public void testGetAllStudentRecords() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        /**
         * Example 1 - GET all the existing student's record
         */
//        LOG.info("Step - 1 : Send GET Request");
//        Response response = RestAssured.given().get(url).andReturn();
//
//        LOG.info("Step - 2 : Print the JSON response body");
//        response.getBody().prettyPrint();
//
//        LOG.info("Step - 3 : Assert StatusCode = 200");
//        assertEquals(response.getStatusCode(), 200, "http status code");
//
//        LOG.info("Step - 4 : Verify that the response contains id = 101");
//        LOG.info("list of Student's Id " +response.getBody().jsonPath().getList("id"));
//        assertTrue(response.getBody().jsonPath().getList("id").contains(101));

        LOG.info("Step - 1 : Send GET Request");
        Response response = RestAssured.given().get(url).andReturn();

        LOG.info("Step - 2 : Print the JSON response body");
        response.getBody().prettyPrint();

        LOG.info("Step - 3 : Assert StatusCode = 200");
        assertTrue(response.getStatusCode()==200);

        LOG.info("Step - 4 :Create a JSONPath object");
        JsonPath jpath = response.jsonPath();

        LOG.info("Step - 5 :Use JsonPath to get the list of all Student's first_name");
        // In java code you DO NOT have to write expression starting with `$`
        List<String> firstNames = jpath.get("first_name");
        LOG.info("List of all Student's first name: " +firstNames.toString());

        LOG.info("Step - 6 :Use JsonPath to get the first_name of the first Student record");
        String firstName = jpath.get("first_name[0]");
        LOG.info("Print the first name of the first Student record: " +firstName);
    }

    @Test
    public void testAStudentRecord() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 1 Get a Student's record with a specific Id.");
        String url1 = url + "/" + "101";
        Response response1 = RestAssured.given()
                .get(url1)
                .andReturn();

        LOG.info(response1.getStatusLine());
        assertTrue(response1.getStatusCode()==200);
        Long id = response1.getBody().jsonPath().getLong("id");
        assertTrue(id==101);

        LOG.info("Step - 2 Get a Students record using matching gender");
        Response response = RestAssured
                .given()
                .queryParam("gender", "male")
                .get(url)
                .andReturn();
        response.getBody().prettyPrint();
    }
}