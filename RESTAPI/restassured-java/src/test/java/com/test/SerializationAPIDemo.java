package com.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SerializationAPIDemo {

    private static final Logger LOG = LoggerFactory.getLogger(SerializationAPIDemo.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer id;

    @Test
    public void serializationTest() throws IOException {

        // creating `Student` object
        Student student = new Student("Sam", "Bailey", "Female");

        // converting `Student` object to JSON string using `ObjectMapper`
        byte[] data = MAPPER.writeValueAsBytes(student);
        String json = MAPPER.writeValueAsString(student);

        LOG.info("serialization of `Student` class into JSON string using `ObjectMapper` => {}", new String(data));
        LOG.info("serialization of `Student` class into JSON string using `ObjectMapper` => {}", json);

        // using `Student` object in body of `CreateStudent` API
        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students";
        Response response = RestAssured.given()
                .contentType("application/json")
                .log().all(true)
                .accept("application/json")
                .body(student)
                .post(url)
                .andReturn();

        // validating the HTTP status code
        assertEquals(response.getStatusCode(), 201, "http status");

        // saving the `id` of the created `Student` to delete the same in cleanup method
        id = response.path("id");

        // validating whether the created `Student` id not null
        assertNotNull(id, "created student id is null");

        LOG.info("created student id => {}", id);
    }

    @AfterMethod
    public void deleteUser() {
        if (id != null) {
            String url = "http://ezifyautomationlabs.com:6565/educative-rest/students/{id}";
            Response response = RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .pathParam("id", id)
                    .delete(url);
            assertEquals(response.getStatusCode(), 204, "http status");
        }
    }

    @Test
    public void deserializationTest() throws IOException {

        String json = "{\"id\":100,\"gender\":\"Female\",\"first_name\":\"Sam\",\"last_name\":\"Bailey\"}";

        Student student = MAPPER.readValue(json, Student.class);
        LOG.info("deserialization of JSON string into `Student` class => {}", student);

        String url = "http://ezifyautomationlabs.com:6565/educative-rest/students/{id}";
        Student studentA = RestAssured
                .given()
                .pathParam("id", "100")
                .get(url)
                .as(Student.class);
        LOG.info("deserialization of JSON string into class `Student` => {}", studentA);

        url = "http://ezifyautomationlabs.com:6565/educative-rest/students";
        Student[] studentsArray = RestAssured
                .get(url)
                .as(Student[].class);
        LOG.info("deserialization of JSON string into `Student[]` => {}", Arrays.deepToString(studentsArray));

        Type type = new TypeReference<List<Student>>() {}.getType();

        List<Student> students = RestAssured
                .get(url)
                .as(type);
        LOG.info("deserialization of JSON string into class with type parameter `List<Student>` => {}", students);

    }

}

class Student {

    public Student() {
    }

    public Student(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    Long id;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Student [id=%s, firstName=%s, lastName=%s, gender=%s]", id, firstName, lastName, gender);
    }

}
