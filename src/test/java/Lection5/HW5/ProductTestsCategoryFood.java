package Lection5.HW5;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.dto.Product;
import ru.enums.CategoryType;
import ru.service.CategoryService;
import ru.service.ProductService;
import ru.utils.PrettyLogger;
import ru.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Тесты для категория\"Еда\"")
public class ProductTestsCategoryFood {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }

    @DisplayName("Добавление продукта")
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @DisplayName("Получение инфорамации с ошибкой 404")
    @Test
    void getProduct404() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Product> response = productService.getProduct(id).execute();

        assertThat(response.body(), equalTo(null));
    }

    @DisplayName("Данные о добавленном продукте")
    @Test
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();

        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }


    @DisplayName("Удаление продукта")
    @Test
    void deleteProducts() throws IOException {
        Integer id = CategoryType.FOOD.getId();

        Response<Product> response = productService.deleteProduct(id).execute();

        assertThat(response.body(), equalTo(null));
    }
}
