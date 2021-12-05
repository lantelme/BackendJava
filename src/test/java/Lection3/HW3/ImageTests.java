package Lection3.HW3;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class ImageTests {
    private final String PATH_TO_IMAGE = "src/test/resources/unnamed.jpg";
    static Map<String, String> headers = new HashMap<>();
    static String encodedFile;
    String uploadedImageId;

    @BeforeAll
    static void setUp() {
        headers.put("Authorization", "Bearer 9d2306f677fa45ecbbe39df15c86f710fb9692fc");
    }

    @BeforeEach
    void beforeTest() {
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
    }

    //Загрузка byte64
    @Test
    void uploadImageByte64() {
        uploadedImageId = given()
                .headers(headers)
                .multiPart("image", encodedFile)
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    //Загрузка обычного изображения
    @Test
    void uploadImageFile() {
        uploadedImageId = given()
                .headers(headers)
                .multiPart("image", new File("src/test/resources/unnamed.jpg"))
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    //Загрузка URL-картинки
    @Test
    void uploadImageURL() {
        uploadedImageId = given()
                .headers(headers)
                .multiPart("image", "https://lapkins.ru/upload/iblock/130/1309222f033fb6928ea065578276ab44.jpg")
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    //Загрузка GIF-картинки
    @Test
    void uploadImageGIF() {
        uploadedImageId = given()
                .headers(headers)
                .multiPart("image", new File("src/test/resources/54556.gif"))
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    //Добавление GIF-изображения в избранные
    @Test
    void favouriteGIFImage() {
        uploadImageGIF();

        given()
                .headers(headers)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath();
    }

    //Добавление Byte64 изображения в избранные
    @Test
    void favouriteByte64Image() {
        uploadImageByte64();

        given()
                .headers(headers)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath();
    }

    //Добавление URL-изображения в избранные
    @Test
    void favouriteURLImage() {
        uploadImageURL();

        given()
                .headers(headers)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath();
    }

    //Добавление изображения в избранные
    @Test
    void favouriteImage() {
        uploadImageFile();

        given()
                .headers(headers)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath();
    }

    //Получение информации об URL-изображении
    @Test
    void getInfoImageURL() {
        uploadImageURL();

        given()
                .headers(headers)
                .expect()
                .statusCode(200)
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/image/" + uploadedImageId)
                .prettyPeek();
    }

    //Получение информации об GIF-изображении
    @Test
    void getInfoImageGIF() {
        uploadImageGIF();

        given()
                .headers(headers)
                .expect()
                .statusCode(200)
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/image/" + uploadedImageId)
                .prettyPeek();
    }

    //Удаление изображений
    @AfterEach
    void tearDown() {
        given()
                .headers(headers)
                .when()
                .delete("https://api.imgur.com/3/image/" + uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    private byte[] getFileContent() {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}


//    //Обновление информации об изображении
//    @Test
//    void UpdateImageInformation (){

//       given()
//                .headers(headers)
//                .log()
//                .method()
//                .log()
//                .uri()
//                .expect()
//                .body("data.title",equalTo("cat"))
//                .contentType("application/json")
//                .when()
//                .post("https://api.imgur.com/3/image/"+uploadedImageId)
//                .prettyPeek();