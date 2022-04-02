package cucumber.stepdefs;

import com.company.config.ApiEndpoints;
import com.company.config.ApiEnvironments;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiretoStepdefs {

    private RequestSpecification request;
    private Response response;

    @Given("System is ready to send request")
    public void systemIsReadyToSendRequest() {
        request = given();
    }
    @When("System sends a request to creates a new auth token")
    public void systemSendsARequestToCreatesANewAuthToken(Map<String, String> auth) {
    /* response = given()
             .contentType(ContentType.JSON)
        .body(new File("./auth.json")).log().all()
              .post(ApiEndpoints.CreateToken);
*/

        response = given()
                .contentType(ContentType.JSON)
                .body(auth).log().all()
                .post(ApiEndpoints.CreateToken);

    }
    @Then("The response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    @When("System sends a request to get list booking")
    public void systemSendsARequestToGetListBooking() {
        response = given()
                .contentType(ContentType.JSON)
                .get(ApiEndpoints.Getbooking);
    }

     @Then("The response <bookingid> should be")
    public void theResponseBookingidShouldBe(Map<String, String> expectedData) {
        JsonPath actualData = new JsonPath(response.getBody().asString());
        Assert.assertEquals("El bookingid no es el correcto",Integer.parseInt(expectedData.get("id")),actualData.getInt("bookingid"));

    }


    @And("System should responds with response data")
    public void systemShouldRespondsWithResponseData(Map<String, String> expectedData) {
        JsonPath actualData = new JsonPath(response.getBody().asString());
        Assert.assertEquals("El id no es el correcto",Integer.parseInt(expectedData.get("bookingid")),Integer.parseInt(actualData.get("1")));

    }
}
