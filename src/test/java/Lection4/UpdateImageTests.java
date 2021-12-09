package Lection4;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ImageResponse.dto.PostImageResponse;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.Endpoints.UPLOAD_IMAGE;

public class UpdateImageTests extends BaseTest {
    private final String PATH_TO_IMAGE = "src/test/resources/unnamed.jpg";
    private String imageId;
    private MultiPartSpecification base64MultiSpec;
    static String encodedFile;
    private Response response;
    private String deleteHash;
    RequestSpecification requestSpecificationWithAuthWithBase64;

    @BeforeEach
    void setUp() {
        byte[] byteArray = getFileContent(PATH_TO_IMAGE);
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        base64MultiSpec = new MultiPartSpecBuilder(encodedFile)
                .controlName("image") //имя поля
                .build();

        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addMultiPart(base64MultiSpec)
                .build();

        response = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .response();

        imageId = response.jsonPath().getString("data.id");
        deleteHash = response.jsonPath().getString("data.deletehash");

    }

    @DisplayName("Изменение ")
    @Test
    void updateFileTest() {
        given()
                .headers("Authorization", token)
                .param("title", "hearts")
                .expect()
                .statusCode(200)
                .when()
                .put("https://api.imgur.com/3/image/{imageHash}", imageId)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response();
        String title = given()
                .headers("Authorization", token)
                .expect()
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}", imageId)
                .prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(PostImageResponse.class)
                .getData().getTitle();

        assertThat("Title", title, equalTo("hearts"));
    }

    private byte[] getFileContent(String PATH_TO_IMAGE) {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(this.PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}
