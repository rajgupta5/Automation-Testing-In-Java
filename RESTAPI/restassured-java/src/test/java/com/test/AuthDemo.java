package com.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AuthDemo {

    private static Logger LOG = LoggerFactory.getLogger(AuthDemo.class);

    /**
     * Basic authentication using Valid username and password
     */
    @Test
    public void test_authentication_ValidCredentials() {
        String url = "http://ezifyautomationlabs.com:6565/educative-rest/auth/students";

        String valid_userName = "testuser";
        String valid_password = "testpass";

        Response response =  RestAssured
                .given()
                .auth().basic(valid_userName, valid_password)
                .when()
                .get(url)
                .thenReturn();

        LOG.info("It will return a valid response");
        response.getBody().prettyPrint();
        assertTrue(response.getStatusCode() == 200);
    }

    /**
     * Basic authentication using In-valid username and password
     */
    @Test
    public void test_authentication_InvalidCredentials() {
        String url = "http://ezifyautomationlabs.com:6565/educative-rest/auth/students";

        String invalid_userName = "testuser1";
        String valid_password = "testpass";

        Response response =  RestAssured
                .given()
                .auth().basic(invalid_userName, valid_password)
                .when()
                .get(url)
                .thenReturn();

        LOG.info("It will return authorization error 401");
        response.getBody().prettyPrint();
        assertTrue(response.getStatusCode() == 401);
    }

    /**
     * Basic authentication using Auth token
     */
    @Test
    public void test_authentication_AuthToken() {

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/auth/students";

        String authCode = "Basic dGVzdHVzZXI6dGVzdHBhc3M=";

        Response response =  RestAssured
                .given()
                .header("authorization", authCode)
                .when()
                .get(url)
                .thenReturn();

        LOG.info("It will return a valid response");
        response.getBody().prettyPrint();
        assertTrue(response.getStatusCode() == 200);
    }
}
