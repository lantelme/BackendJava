//package Lection4.HW4;
//
//import io.restassured.builder.MultiPartSpecBuilder;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.response.Response;
//import io.restassured.specification.MultiPartSpecification;
//import io.restassured.specification.RequestSpecification;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.commons.io.FileUtils;
//import org.junit.jupiter.api.DisplayName;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Base64;
//
//import static io.restassured.RestAssured.given;
//import static ru.Endpoints.UPLOAD_IMAGE;
//
//@Getter
//@Setter
//@DisplayName("Загрузка файлов")
//public class ImageUploadTests extends BaseTest {
//    private final String PATH_TO_IMAGE = "src/test/resources/unnamed.jpg";
//    private final String PATH_TO_GIF = "src/test/resources/54556.gif";
//
//    private MultiPartSpecification base64MultiSpec;
//    private MultiPartSpecification URLMultiSpec;
//    private MultiPartSpecification GIFMultiSpec;
//    private MultiPartSpecification imageMultiSpec;
//
//    static String encodedFile;
//
//     Response responseBase64;
//     Response responseURL;
//     Response responseGIF;
//     Response responseImage;
//
//    RequestSpecification requestSpecificationWithAuthWithBase64;
//    RequestSpecification requestSpecificationWithAuthWithURL;
//    RequestSpecification requestSpecificationWithAuthWithGIF;
//    RequestSpecification requestSpecificationWithAuthWithImage;
//
//    void uploadImageTests() {
//        byte[] byteArray = getFileContent(PATH_TO_IMAGE);
//        encodedFile = Base64.getEncoder().encodeToString(byteArray);
//
//        base64MultiSpec = new MultiPartSpecBuilder(encodedFile)
//                .controlName("image")
//                .build();
//
//        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
//                .addHeader("Authorization", token)
//                .addFormParam("title", "fefeffe")
//                .addMultiPart(base64MultiSpec)
//                .build();
//
//        responseBase64 = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
//                .post(UPLOAD_IMAGE)
//                .prettyPeek()
//                .then()
//                .extract()
//                .response();
//
//        URLMultiSpec = new MultiPartSpecBuilder("https://lapkins.ru/upload/iblock/130/1309222f033fb6928ea065578276ab44.jpg")
//                .controlName("image")
//                .build();
//
//        requestSpecificationWithAuthWithURL = new RequestSpecBuilder()
//                .addHeader("Authorization", token)
//                .addMultiPart(URLMultiSpec)
//                .build();
//
//        responseURL = given(requestSpecificationWithAuthWithURL, positiveResponseSpecification)
//                .post(UPLOAD_IMAGE)
//                .prettyPeek()
//                .then()
//                .extract()
//                .response();
//
//
//        GIFMultiSpec = new MultiPartSpecBuilder(new File(PATH_TO_GIF))
//                .controlName("image")
//                .build();
//
//        requestSpecificationWithAuthWithGIF = new RequestSpecBuilder()
//                .addHeader("Authorization", token)
//                .addFormParam("title", "Picture")
//                .addFormParam("type", "gif")
//                .addMultiPart(GIFMultiSpec)
//                .build();
//
//        responseGIF = given(requestSpecificationWithAuthWithGIF, positiveResponseSpecification)
//                .post(UPLOAD_IMAGE)
//                .prettyPeek()
//                .then()
//                .extract()
//                .response();
//
//        imageMultiSpec = new MultiPartSpecBuilder(new File(PATH_TO_IMAGE))
//                .controlName("image")
//                .build();
//
//        requestSpecificationWithAuthWithImage = new RequestSpecBuilder()
//                .addHeader("Authorization", token)
//                .addFormParam("title", "catt")
//                .addFormParam("type", "jpg")
//                .addMultiPart(imageMultiSpec)
//                .build();
//
//        responseImage = given(requestSpecificationWithAuthWithImage, positiveResponseSpecification)
//                .post(UPLOAD_IMAGE)
//                .prettyPeek()
//                .then()
//                .extract()
//                .response();
//
//    }
//
//    private byte[] getFileContent(String PATH_TO_IMAGE) {
//        byte[] byteArray = new byte[0];
//        try {
//            byteArray = FileUtils.readFileToByteArray(new File(this.PATH_TO_IMAGE));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return byteArray;
//    }
//}
