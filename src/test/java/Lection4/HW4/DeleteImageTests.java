package Lection4.HW4;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@DisplayName("Удаление изображений")
public class DeleteImageTests extends BaseTest {
    String imageIdBase64;
    String imageIdURL;
    String imageIdGIF;
    String imageId;

    @BeforeEach
    void setUp() {
        ImageUploadTests imageUploadTests = new ImageUploadTests();
        imageUploadTests.uploadImageTests();

        imageIdGIF = imageUploadTests.responseGIF.jsonPath().getString("data.id");

        imageIdURL = imageUploadTests.responseURL.jsonPath().getString("data.id");

        imageIdBase64 = imageUploadTests.responseBase64.jsonPath().getString("data.id");

        imageId = imageUploadTests.responseImage.jsonPath().getString("data.id");

    }

    @DisplayName("Удаление GIF")
    @Test
    void deleteHashGif() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}",imageIdGIF)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();
    }

    @DisplayName("Удаление Base64")
    @Test
    void deleteHashBase64() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}",imageIdBase64)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();
    }

    @DisplayName("Удаление URL")
    @Test
    void deleteHashURL() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}",imageIdURL)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();
    }
    @DisplayName("Удаление File Image")
    @Test
    void deleteHashFileImage() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}",imageId)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();
    }


}


