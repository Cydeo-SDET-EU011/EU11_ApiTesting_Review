package week4;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.path.xml.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class XmlTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47";
        RestAssured.port = 8000;

    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans");

        response.prettyPrint();
        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(),is("application/xml"));
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();
        assertThat(xmlPath.getInt("List.item[0].id"),is(1));
        assertThat(xmlPath.getString("List.item[2].name"),is("Fidole"));
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.XML)
                .when().get("http://restapi.adequateshop.com/api/Traveler");

        XmlPath xmlPath = response.xmlPath();
        assertThat(xmlPath.getInt("TravelerinformationResponse.page") ,is(1));
        assertThat(xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation[0].name") ,is("Developer"));


    }
}
