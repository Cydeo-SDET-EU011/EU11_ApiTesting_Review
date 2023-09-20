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
}
