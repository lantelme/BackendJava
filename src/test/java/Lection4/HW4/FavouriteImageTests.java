//package Lection4.HW4;
//
//import org.junit.jupiter.api.*;
//
//import static io.restassured.RestAssured.given;
//
//@DisplayName("Добавление изображения в избранные")
//public class FavouriteImageTests extends BaseTest {
//
//    String imageIdBase64;
//    String imageIdURL;
//    String imageIdGIF;
//    String imageId;
//
//    @BeforeEach
//    void setUp() {
//        ImageUploadTests imageUploadTests = new ImageUploadTests();
//        imageUploadTests.uploadImageTests();
//
//        imageIdGIF = imageUploadTests.responseGIF.jsonPath().getString("data.id");
//
//        imageIdURL = imageUploadTests.responseURL.jsonPath().getString("data.id");
//
//        imageIdBase64 = imageUploadTests.responseBase64.jsonPath().getString("data.id");
//
//        imageId = imageUploadTests.responseImage.jsonPath().getString("data.id");
//    }
//
//    @DisplayName("Добавление GIF-изображения в избранные")
//    @Test
//    void favouriteGifImage(){
//        given()
//                .headers("Authorization", token)
//                .when()
//                .post("https://api.imgur.com/3/image/{imageHash}/favorite",imageIdGIF)
//                .prettyPeek()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .jsonPath();
//    }
//
//    @DisplayName("Добавление URL-изображения в избранные")
//    @Test
//    void favouriteURLImage(){
//        given()
//                .headers("Authorization", token)
//                .when()
//                .post("https://api.imgur.com/3/image/{imageHash}/favorite",imageIdURL)
//                .prettyPeek()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .jsonPath();
//    }
//    @DisplayName("Добавление Base64 изображения в избранные")
//    @Test
//    void favouriteBase64Image(){
//        given()
//                .headers("Authorization", token)
//                .when()
//                .post("https://api.imgur.com/3/image/{imageHash}/favorite",imageIdBase64)
//                .prettyPeek()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .jsonPath();
//    }
//    @DisplayName("Добавление File-Image изображения в избранные")
//    @Test
//    void favouriteFileImage(){
//        given()
//                .headers("Authorization", token)
//                .when()
//                .post("https://api.imgur.com/3/image/{imageHash}/favorite",imageId)
//                .prettyPeek()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .jsonPath();
//    }
//
//    @AfterEach
//    void tearDown(){
//        DeleteImageTests deleteImageTests = new DeleteImageTests();
//        deleteImageTests.setUp();
//    }
//
//}