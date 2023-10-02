package week5;

import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MockServerTest {

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("https://d90652f9-c9dc-47f2-be4d-fa73d86a3d67.mock.pstmn.io/categories");

        assertThat(response.statusCode(),is(200));
        response.prettyPrint();
    }
}
