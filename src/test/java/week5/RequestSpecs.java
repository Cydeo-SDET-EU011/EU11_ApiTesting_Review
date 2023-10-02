package week5;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;
import week1.*;

import static io.restassured.RestAssured.given;

public class RequestSpecs {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    RequestSpecification reqSpec = given().accept(ContentType.JSON);
    RequestSpecification searchSpartanReq = given().accept(ContentType.JSON)
            .and().queryParam("nameContains","en");

    ResponseSpecification resSpec = reqSpec.expect().statusCode(200)
            .and().contentType("application/json");

    @Test
    public void test1(){
        Response response = given().spec(reqSpec)
                .when().get("api/spartans");

        response.prettyPrint();
    }

    @Test
    public void test2(){
        Response response = given().spec(searchSpartanReq)
                .when().get("/api/spartans/search")
                .then().spec(resSpec)
                .extract().response();

        response.prettyPrint();
    }
}
