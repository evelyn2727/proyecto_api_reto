package com.company.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;



public class ApiConfig {

    public static RequestSpecification requestSpecification;

    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setUp(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ApiEnvironments.URL_TEST)
                .setBasePath("/")
                .addHeader("Content-Type","application/json")
                 .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();


     /*   responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000L))
                .build();
*/
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    @AfterClass
    public static void cleanUp(){
        RestAssured.reset();
    }
}
