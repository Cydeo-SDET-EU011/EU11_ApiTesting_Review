package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class PatchMethod {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("body as string")
    public void test1(){
        String bodyString = "{\n" +
                "  \"phone\": 3698521478\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyString)
                .when().patch("/api/spartans/126");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }}
