package Lection6.HW6;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.db.dao.CategoriesMapper;
import ru.db.dao.ProductsMapper;
import ru.db.model.Categories;
import ru.dto.Category;
import ru.dto.Product;
import ru.enums.CategoryType;
import ru.service.CategoryService;
import ru.service.ProductService;
import ru.utils.DbUtils;
import ru.utils.PrettyLogger;
import ru.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Тесты для категория\"Еда\"")
public class CategoryTests {
    int productId;
    static Retrofit client;
    static CategoriesMapper categoriesMapper;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
        categoriesMapper = DbUtils.getCategoriesMapper();
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @DisplayName("Получение категории продуктов")
    @Test
    void postProduct() throws IOException {
        productId = CategoryType.ELECTRONIC.getId();
        Categories category = categoriesMapper.selectByPrimaryKey(productId);
        Response<Category> response = categoryService.getCategory(category.getId()).execute();

        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getTitle(), Matchers.equalTo(CategoryType.ELECTRONIC.getTitle()));
        assertThat(response.body().getId(),equalTo(category.getId()));
    }
}
