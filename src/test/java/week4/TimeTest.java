package week4;

import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class TimeTest {

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.152.219.47:8000/api/spartans/10")
                .then().time(both(greaterThan(100L)).and(lessThan(2000L))).extract().response();
    }
}

