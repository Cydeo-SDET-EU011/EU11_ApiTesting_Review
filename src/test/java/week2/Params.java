package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;


public class Params {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("use path params")
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",1)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
    }

    @Test
    @DisplayName("use query params")
    public void test2(){
        Response responseName = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","ha")
                .when().get("/api/spartans/search");

        //responseName.prettyPrint();

        Response responseGender = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .when().get("/api/spartans/search");

        responseGender.prettyPrint();
    }

    @Test
    @DisplayName("search by name and gender")
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Male")
                .and().queryParam("nameContains","da")
                .when().get("/api/spartans/search");

        response.prettyPrint();

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("nameContains","da");
        queryParams.put("gender","Male");

        Response responseMap = given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("/api/spartans/search");

        responseMap.prettyPrint();



    }
}
