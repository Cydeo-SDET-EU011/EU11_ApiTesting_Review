package offieHour.week2.steps;

import io.cucumber.java.en.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import io.restassured.specification.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StepDefinition {

    RequestSpecification reqSpec;
    Response response;

    String state;

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
        if(state != null){
            response = reqSpec.when().get("https://www.zippopotam.us" + endPoint + "/{state}/{city}");
        }else{
            response = reqSpec.when().get("https://www.zippopotam.us" + endPoint + "/{zipcode}");
        }

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

    @Given("path state is {string}")
    public void path_state_is(String state) {
        this.state = state;
        reqSpec.and().pathParam("state",state);
    }
    @Given("path city is {string}")
    public void path_city_is(String city) {
        reqSpec.and().pathParam("city",city);
    }
    @Then("each places must contains {string} as a value")
    public void each_places_must_contains_as_a_value(String cityName) {
        JsonPath jsonPath = response.jsonPath();
        List<String> cityNames = jsonPath.getList("places.'place name'");
        assertThat(cityNames,everyItem(containsString(cityName)));
    }

    @Then("each post code must start with {string}")
    public void each_post_code_must_start_with(String num) {
        JsonPath jsonPath = response.jsonPath();
        List<String> postCodes = jsonPath.getList("places.'post code'");
        assertThat(postCodes,everyItem(startsWith(num)));
    }

}
