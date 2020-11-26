package com.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

public class RequestSpecBuilderDemo {

    private static Logger LOG = LoggerFactory.getLogger(RequestSpecBuilderDemo.class);

    @Test
    public void test_RequestSpecificationWithQueryParam() {

        LOG.info("Step - 2 : Make a get() call using RequestSpecification to fetch John's record");
        Response response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .queryParam("first_name", "John")
                .when()
                .get();

        LOG.info("Step - 3 : Print the JSON response body");
        response.getBody().prettyPrint();

    }

    @Test
    public void test_RequestSpecification() {

        LOG.info("Step - 4 : Make a get() call using RequestSpecification to fetch all Student's record");
        Response response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .when()
                .get();

        LOG.info("Step -5 : Print the JSON response body");
        response.getBody().prettyPrint();

    }


    @Test
    public void test_ResponseSpecification1() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 2 : Make a get() call using ResponseSpecification and validate status 200 ");
        RestAssured
                .when()
                .get(url)
                .then()
                .spec(getResponseSpecification());
    }


    @Test
    public void test_ResponseSpecification2() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";

        LOG.info("Step - 3 : Make a get() call using ResponseSpecification and validate status code and Student's first name is John ");
        RestAssured
                .when()
                .get(url)
                .then()
                .spec(getResponseSpecification())
                .body("John", is(anything()));
    }

    @Test
    public void test_combineRequestResponseSpecification() {

        LOG.info("Step - 1 : Make a get() call using ResponseSpecification and ResponseSpecification ");
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .when()
                .get()
                .then()
                .spec(getResponseSpecification());
    }



    // Helper method
    public ResponseSpecification getResponseSpecification() {

        LOG.info("Step - 1 : Create ResponseSpecification using ResponseSpecBuilder ");
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);

        ResponseSpecification responseSpec = builder.build();
        return responseSpec;
    }

    // Helper method
    public RequestSpecification getRequestSpecification() {

        LOG.info("Step - 1 : Create RequestSpecification using  RequestSpecBuilder ");
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri ("http://ezifyautomationlabs.com:6565");
        builder.setBasePath ("/educative-rest/students");

        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }
}
