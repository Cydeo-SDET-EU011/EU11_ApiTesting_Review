package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class PathMethod {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/1");

        response.prettyPrint();

        int id = response.path("id");
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

        long phone = response.path("phone");
        System.out.println("phone = " + phone);

                                        //int       //Integer
        Assertions.assertEquals(Integer.valueOf(1),response.path("id"));
        Assertions.assertEquals("Meade",response.path("name"));
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();
        List<Integer> ids  = response.path("id");
        System.out.println("spartan1Id = " + ids);
        int spartn1Id = response.path("id[0]");
        System.out.println("spartn1Id = " + spartn1Id);

        String spartan3Name = response.path("name[2]");
        System.out.println("spartan3Name = " + spartan3Name);
    }
}
