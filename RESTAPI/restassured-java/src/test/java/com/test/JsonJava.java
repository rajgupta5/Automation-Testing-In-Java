package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

public class JsonJava {

    @Test
    public void whenSerializeAndDeserializeUsingJackson_thenCorrect()
            throws IOException {
        Foo foo = new Foo(1,"first");
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = mapper.writeValueAsString(foo);
        Foo result = mapper.readValue(jsonStr, Foo.class);
        assertEquals(foo.getId(),result.getId());
    }

    @Test
    public void whenSerializeAndDeserializeUsingGson_thenCorrect(){
        Gson gson = new Gson();
        Foo foo = new Foo(1,"first");

        String jsonStr = gson.toJson(foo);
        Foo result = gson.fromJson(jsonStr, Foo.class);
        assertEquals(foo.getId(),result.getId());
    }

    @Test
    public void whenSerializeUsingJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(new File("target/car.json"), car);

    }

    @Test
    public void whenDeserializeUsingJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = objectMapper.readValue(json, Car.class);
        Car car1= objectMapper.readValue(new File("src/JavaTest/resources/json_car.json"), Car.class);
        Car car2 = objectMapper.readValue(new URL("file:src/JavaTest/resources/json_car.json"), Car.class);

        System.out.println(car);
        System.out.println(car1);
        System.out.println(car2);
    }

    @Test
    public void JsonArrayStringToJavaList() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        System.out.println(listCar);

        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map
                = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
        System.out.println(map);


    }

    @Test
    public void jsonParser() {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(json);
        System.out.println(jsonObject);
    }

    @Test
    public void configureTest() throws JsonProcessingException {
        String jsonString
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);


        Car car = objectMapper.readValue(jsonString, Car.class);

        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();
    }



}
