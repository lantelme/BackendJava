package Lection3;

import Lection3.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTests extends BaseTest {
        static Map<String, String> headers = new HashMap<>();
        String username = "loremIpsum";

        @BeforeAll
        static void setUp() {
            headers.put("Authorization", "Bearer 9d2306f677fa45ecbbe39df15c86f710fb9692fc");
        }

    @Test
    void getAccountInfoTest11() {
        given()
                .headers(headers)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .statusCode(200);
    }


    @Test
    void getAccountInfoWithLoggingTest(){
        given()
                .header("Authorization","Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{{username}}", username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoWithAssertionsInGivenTest(){
        given()
                .header("Authorization","Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .body("data.url",equalTo(username))
                .body("seccess",equalTo(true))
                .body("status",equalTo(200))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/{{username}}", username)
                .prettyPeek();
    }

    @Test
    void getAccountInfoWithAssertionAfterTest(){
        Response response = given()
                .header("Authorization","Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{{username}}", username)
                .prettyPeek();

        assertThat(response.jsonPath().get("data.url"),equalTo(username));
    }

}
