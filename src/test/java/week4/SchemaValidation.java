package week4;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/10")
                .then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath
                        ("singleSpartanSchema.json"))
                .extract().response();
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().body(JsonSchemaValidator.matchesJsonSchema
                        (new File("C:\\Users\\Zulpikar\\IdeaProjects\\EU11_ApiTesting_Review\\src" +
                                "\\test\\java\\week4\\allSpartanSchema.json"))).extract().response();


    }
}
