package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.Matchers.is;

public class JsonToJava {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("convert response to map")
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/1");

        Map<String, Object> spartan1 = response.as(Map.class);
        System.out.println(spartan1);
        MatcherAssert.assertThat(spartan1.get("name").toString(), is("Meade"));
    }

    @Test
    @DisplayName("convert response to list")
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String, Object>> allSpartans = response.as(List.class);
//        System.out.println(allSpartans);
//        for (Map<String, Object> spartan : allSpartans) {
//            System.out.println(spartan);
//
//        }

        MatcherAssert.assertThat(allSpartans.get(5).get("name").toString(),is("Tedmund"));
    }
}
