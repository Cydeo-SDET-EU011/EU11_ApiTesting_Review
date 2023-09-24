package week4;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.given;

public class ParametrizationTest {

     /*
    we have 4 ways to provide data to test
    1. value source
    2. method source
    3. csv source
    4. csv file source

     */


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @ParameterizedTest
    @DisplayName("value source")
    @ValueSource(ints = {1,5,9,2,7})
    public void test1(int id){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/" + id );
        response.prettyPrint();
        }
    }



