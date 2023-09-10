package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class ZipCodePractice {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://api.zippopotam.us";
    }

    @Test
    @DisplayName("get city by postal code")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/us/22012");

        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

        String actualPostalCode = jsonPath.getString("country");
        String actualState = jsonPath.getString("places[0].state");

        Assertions.assertEquals("United States",actualPostalCode);
        System.out.println("actualState = " + actualState);

    }
}
