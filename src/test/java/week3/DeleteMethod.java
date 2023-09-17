package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class DeleteMethod {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("delete a spartan")
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().delete("/api/spartans/125");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }
}
