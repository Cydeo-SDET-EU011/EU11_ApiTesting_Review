package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class BookitAuth {

    String token;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://qa.api.book-it.today";
    }


    @Test
    @DisplayName("Get token")
    @Order(1)
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("email","blyst6@si.edu")
                .and().queryParam("password","barbabaslyst")
                .when().get("https://qa.api.book-it.today/sign");

        response.prettyPrint();
        token = "Bearer " + response.path("accessToken");
        System.out.println("token = " + token);
    }

    @Test
    @DisplayName("get all campuses")
    public void test2(){

        token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTUxNiIsImF1ZCI6InRlYWNoZXIifQ.saFcTsRyMJQj1e8jhya1zpxngBggh5fC3lGsGyBCrQs";
        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .when().get("/api/campuses");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }

}
