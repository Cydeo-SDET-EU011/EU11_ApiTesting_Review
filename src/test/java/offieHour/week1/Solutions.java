package offieHour.week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

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
    }

    /*
    TASK 4 :
- Given accept type is Json
- Path param value- US
- When users sends request to /countries/us
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
 And Region_id is 2

     */

    @Test
    public void task4(){
        given().accept(ContentType.JSON)
                .and().pathParam("value","US")
                .when().get("/countries/{value}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("country_id",is("US"))
                .body("country_name",is("United States of America"))
                .body("region_id",is(2));
    }

    /*
TASK 5 :
 - Given accept type is Json
 - Query param value - q={"department_id":80}
 - When users sends request to /employees
 - Then status code is 200
 - And Content - Type is Json
 - And all job_ids start with 'SA'
 - And all department_ids are 80
 - Count is 25
     */

    @Test
    public void task5(){
        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"department_id\":80}")
                .when().get("/employees")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("items.job_id",everyItem(startsWith("SA")))
                .and().body("items.department_id",everyItem(is(80)))
                .body("count",is(25));
    }

    /*
     TASK 6 :
 - Given accept type is Json
 - Query param value q={“region_id":3}
 - When users sends request to /countries
 - Then status code is 200
 - And all regions_id is 3
 - And count is 6
 - And hasMore is false
 - And Country_name are;
 Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void task6(){
        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":3}")
                .when().get("/countries/")
                .then().statusCode(200)
                .and().contentType("application/json")
                .body("items.region_id",everyItem(is(3)))
                .body("count",is(6))
                .body("hasMore",is(false))
                .body("items.country_name",containsInRelativeOrder("Australia","China","India","Japan","Malaysia","Singapore"));
//                .body("items.country_name",is(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore")));



    }
}
