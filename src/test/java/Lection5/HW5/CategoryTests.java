package Lection5.HW5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.dto.Category;
import ru.dto.Product;
import ru.enums.CategoryType;
import ru.service.CategoryService;
import ru.service.ProductService;
import ru.utils.PrettyLogger;
import ru.utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Тесты для категория\"Еда\"")
public class CategoryTests {
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

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @DisplayName("Получение категории продукта")
    @Test
    void postProduct() throws IOException {
            Integer id = CategoryType.FOOD.getId();
            Response<Category> response = categoryService.getCategory(id).execute();

            PrettyLogger.DEFAULT.log(response.body().toString());

            assertThat(response.body().getTitle(),equalTo(CategoryType.FOOD.getTitle()));
    }
}

