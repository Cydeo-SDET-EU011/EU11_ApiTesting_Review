package week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

public class SpartanTest {

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("http://54.152.219.47:8000/api/spartans");

        response.prettyPrint();
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("http://54.152.219.47:8000/api/spartans");

        int statusCode = response.statusCode();
        Assertions.assertEquals(200,statusCode);
    }

    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.XML)
                .when().get("http://54.152.219.47:8000/api/spartans");

        String expected = "application/xml";
        String actual = response.contentType();
        Assertions.assertEquals(expected,actual);
    }


}
