package cucumber;


import com.company.config.ApiEnvironments;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class Hooks {

    public static RequestSpecification requestSpecification;

    public static ResponseSpecification responseSpecification;

    @Before("@ApiRest")
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

    @Before("@UITest")
    public static void uiTest(){
        System.out.println("Este es un UI Test");
    }

    @After("@ApiRest")
    public static void cleanUp(){
        RestAssured.reset();
    }

}
