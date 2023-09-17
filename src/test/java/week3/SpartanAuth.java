package week3;

import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class SpartanAuth {

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("user","user")
                .when().get("http://54.152.219.47:7000/api/spartans");

        System.out.println(response.statusCode());
    }

    @Test
    public void test2(){
            String bodyString = "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"Jack\",\n" +
                    "  \"phone\": 3698521478\n" +
                    "}";

            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().auth().basic("admin","admin")
                    .and().body(bodyString)
                    .when().post("http://54.152.219.47:7000/api/spartans");

        System.out.println(response.statusCode());
    }
}
