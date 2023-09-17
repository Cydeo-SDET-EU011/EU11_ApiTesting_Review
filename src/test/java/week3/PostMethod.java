package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week3.POJO.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class PostMethod {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("body as string")
    public void test1(){
        String bodyString = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Jack\",\n" +
                "  \"phone\": 3698521478\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyString)
                .when().post("/api/spartans");

        response.prettyPrint();
    }

    @Test
    @DisplayName("body as map")
    public void test2(){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("name","Hanna");
        bodyMap.put("gender","Female");
        bodyMap.put("phone",6547893215L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyMap)
                .when().post("/api/spartans");

        response.prettyPrint();
        String msg = "A Spartan is Born!";
        Assertions.assertEquals(msg,response.path("success"));
    }

    @Test
    @DisplayName("body as pojo")
    public void test3(){
        SingleSpartan bodyPojo = new SingleSpartan();
        bodyPojo.setName("Ahmet");
        bodyPojo.setGender("Male");
        bodyPojo.setPhone(9874563145L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyPojo)
                .when().post("/api/spartans");

        response.prettyPrint();
    }
}
