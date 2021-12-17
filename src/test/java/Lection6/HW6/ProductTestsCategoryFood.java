package Lection6.HW6;

import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.db.dao.ProductsMapper;
import ru.db.model.Products;
import ru.dto.Product;
import ru.enums.CategoryType;
import ru.service.CategoryService;
import ru.service.ProductService;
import ru.utils.DbUtils;
import ru.utils.PrettyLogger;
import ru.utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Тесты для категория\"Еда\"")
public class ProductTestsCategoryFood {
    long productId;
    static Retrofit client;
    static ProductsMapper productsMapper;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
        productsMapper = DbUtils.getProductsMapper();
    }

    @DisplayName("Добавление продукта")
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }


    @DisplayName("Данные о добавленном продукте")
    @Test
    void postProduct() throws IOException {
        ProductsMapper mapper = DbUtils.getProductsMapper();

        Response<Product> response = productService.createProduct(product).execute();
        productId = response.body().getId();
        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getId(), Matchers.equalTo((mapper.selectByPrimaryKey(productId)
                .getId()
                .intValue())));
    }


    @DisplayName("Количество продуктов")
    @Test
    void countProduct() throws IOException {
        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        Response<Product> response = productService.createProduct(product).execute();
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);

        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(countProductsAfter, Matchers.equalTo(countProductsBefore+1));
    }

    @DisplayName("Удаление продукта")
    @Test
    void deleteProducts() throws IOException {
        productId = CategoryType.FOOD.getId();
        int deleteProducts = productsMapper.deleteByPrimaryKey(productId);
        Response<Product> response = productService.deleteProduct(deleteProducts).execute();
        assertThat(response.body(), Matchers.equalTo(null));
    }

}
