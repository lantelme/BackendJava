//package Lection5;
//
//import com.github.javafaker.Faker;
//import org.apache.groovy.json.internal.IO;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import ru.dto.Category;
//import ru.dto.Product;
//import ru.enums.CategoryType;
//import ru.service.CategoryService;
//import ru.service.ProductService;
//import ru.utils.PrettyLogger;
//import ru.utils.RetrofitUtils;
//
//
//import java.io.IOException;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ProductTests {
//    static Retrofit client;
//    static ProductService productService;
//    static CategoryService categoryService;
//    Faker faker = new Faker();
//    Product product;
//
//    @BeforeAll
//    static void beforeAll() {
//        client = RetrofitUtils.getRetrofit();
//        productService = client.create(ProductService.class);
//        categoryService = client.create(CategoryService.class);
//    }
//
//    @BeforeEach
//    void setUp() {
//        product = new Product()
//                .withTitle(faker.food().sushi())
//                .withPrice((int) ((Math.random() + 1) * 100))
//                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle());
//    }
//
//    @Test
//    void postProductTest() throws IOException{
//        Response<Product> response = productService.createProduct(product).execute();
//
//
//        assertThat(response.body().getTitle(),equalTo(product.getTitle()));
//        assertThat(response.body().getPrice(),equalTo(product.getPrice()));
//        assertThat(response.body().getCategoryTitle(),equalTo(product.getCategoryTitle()));
//    }
//
//    @Test
//    void getCategoryByIdTest() throws IOException{
//        Integer id = CategoryType.ELECTRONIC.getId();
//
//        Response<Category> response = categoryService
//                .getCategory(id)
//                .execute();
//        PrettyLogger.DEFAULT.log(response.body().toString());
//
//        assertThat(response.body().getTitle(),equalTo(CategoryType.ELECTRONIC.getTitle()));
//        assertThat(response.body().getId(),equalTo(id));
//    }
//}
