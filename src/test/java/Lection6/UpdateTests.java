//package Lection6;
//
//import com.github.javafaker.Faker;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import ru.db.dao.ProductsMapper;
//import ru.dto.Product;
//import ru.enums.CategoryType;
//import ru.service.CategoryService;
//import ru.service.ProductService;
//import ru.utils.DbUtils;
//import lombok.*;
//import ru.utils.RetrofitUtils;
//
//import java.io.IOException;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class UpdateTests {
//    int productId;
//    static ProductsMapper productsMapper;
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
//        productsMapper = DbUtils.getProductsMapper();
//    }
//
//    @BeforeEach
//    void setUp() throws IOException {
//        product = new Product()
//                .withTitle(faker.food().dish())
//                .withPrice((int) ((Math.random() + 1) * 100))
//                .withCategoryTitle(CategoryType.FOOD.getTitle());
//
//        Response<Product> response = productService.createProduct(product).execute();
//        productId = response.body().getId();
//        product.setId(productId);
//        product.setPrice(111111111);
//    }
//
//    @SneakyThrows
//    @Test
//    void updateTest(){
//        Response<Product> response = productService.createProduct(product).execute();
//        assertThat("Updated",response.isSuccessful());
//    }
//
//}
