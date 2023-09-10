package week2;

import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class DemoqaBookTest {

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/BookStore/v1/Books");


        Map<String,Object> firstBook = response.jsonPath().getMap("books[0]");
        System.out.println(firstBook);

        Map<String,Object> allBooks = response.as(Map.class);
        System.out.println(allBooks);

        List<Map<String,Object>> allbooksList = response.jsonPath().getList("books");
        System.out.println(allbooksList);
    }


}
