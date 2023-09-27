package offieHour.week2.steps;

import io.cucumber.java.en.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StepDefinition {

    RequestSpecification reqSpec;
    Response response;

    @Given("Accept application\\/json")
    public void accept_application_json() {
        reqSpec = given().accept(ContentType.JSON);
    }

    @Given("path zipcode is {int}")
    public void path_zipcode_is(Integer zipcode) {
        reqSpec.and().pathParam("zipcode", zipcode);
    }

    @When("I send a GET request to {string} endpoint")
    public void i_send_a_get_request_to_endpoint(String endPoint) {
        response = reqSpec.when().get("https://www.zippopotam.us" + endPoint + "/{zipcode}");
    }

    @Then("status code must be {int}")
    public void status_code_must_be(Integer statuscode) {
        response.then().statusCode(statuscode);
    }

    @Then("content type must be {string}")
    public void content_type_must_be(String contentType) {
        response.then().contentType(contentType);

    }

    @Then("{string} header is {string}")
    public void header_is(String header, String value) {
        response.then().header(header, value);
    }

    @Then("{string} header exists")
    public void header_exists(String header) {
        assertThat(response.header(header), is(notNullValue()));
    }

    @Then("body should contains following information")
    public void body_should_contains_following_information(Map<String, String> expectedValues) {
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
                String key = entry.getKey(); // post code
                String value = entry.getValue();
                if(key.equals("place name") || key.equals("state")){
                    assertThat(response.path("places[0]." + "'" + key +"'"),is(value));
                }else{
                    assertThat(response.path("'" + key +"'"),is(value));
                }



        }




    }
}
