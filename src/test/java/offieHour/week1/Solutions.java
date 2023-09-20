package offieHour.week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Solutions {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:1000/ords/hr";
    }

    /*
    Task 1 :
- Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America
     */

    @Test
    public void task1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries/US");

        response.prettyPrint();

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),equalTo("application/json"));
        assertThat(response.body().asString(),containsString("United States of America"));
    }

    /*
    Task 2 :
- Given accept type is Json
- When users sends request to /employees/1
- Then status code is 404
     */

    @Test
    public void task2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees/1");

        System.out.println(response.statusCode());
        assertThat(response.statusCode(),is(404));
    }

    /*
    Task 3 :
- Given accept type is Json
- When users sends request to /regions/1
- Then status code is 200
- And Content - Type is application/json
- And response contains Europe
- And header should contains Date
- And Transfer-Encoding should be chunked

     */

    @Test
    public void task3(){
        given().accept(ContentType.JSON)
                .when().get("/regions/1")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().headers("Transfer-Encoding",is("chunked"))
                .and().body(containsString("Europe"))
                .extract().response().headers().hasHeaderWithName("Date");
        ;

    }
}
