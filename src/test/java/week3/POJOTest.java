package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week3.POJO.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class POJOTest {

    @BeforeAll
    public static void init() {
        //RestAssured.baseURI = "http://54.152.219.47:8000";
    }


    @Test
    @DisplayName("Single spartan")
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/2");

        response.prettyPrint();
        SingleSpartan spartan2 = response.as(SingleSpartan.class);
        System.out.println(spartan2);
        Assertions.assertEquals("Nels",spartan2.getName());
    }

    @Test
    @DisplayName("All Spartans")
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        JsonPath jsonPath = response.jsonPath();
        AllSpartans spartans = jsonPath.getObject("",AllSpartans.class);

        System.out.println(spartans);
    }

    @Test
    @DisplayName("Search spartans")
    public void test3(){
        Response responseName = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","ha")
                .when().get("/api/spartans/search");

        Search searchByName = responseName.as(Search.class);
        System.out.println(searchByName);
    }

    @Test
    @DisplayName("Zippotam Search by postal code")
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://api.zippopotam.us/us/22012");

        SearchByPostCode postCode22012 = response.as(SearchByPostCode.class);
        System.out.println(postCode22012);
        Assertions.assertEquals("US",postCode22012.getCountryAbb());
        Assertions.assertEquals("Virginia",postCode22012.getPlaces().get(0).getState());

    }




}
