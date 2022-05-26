package homework6;

import com.github.javafaker.Faker;
import homework6.api.ProductService;
import homework6.dto.Product;
import homework6.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CreateProductTest {

    static ProductService productService;
    static SqlSession sqlSession;
    static String resource = "mybatis-config.xml";
    static db.model.ProductsExample productsExample;
    Product product = null;
    Product productUpd = null;
    Faker faker = new Faker();
    int id;
    String productName;
    int productPrice;

    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        // POST Request
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id =  response.body().getId();
        productName = response.body().getTitle();
        productPrice = response.body().getPrice();
        assertThat(response.isSuccessful(), CoreMatchers.is( true));
        assertThat(id,notNullValue());
        assertThat(productName,notNullValue());
        System.err.println("Created the new Product with Id-" + id);
        // SQL Request
        productsExample = new db.model.ProductsExample();
        productsExample.createCriteria().andIdEqualTo((long) id); //Продукт с ID существует в БД
        sqlSession.commit();
        System.err.println("The Product with Id-" + id + " have been detected in DB");

        // GET Products request
        Response<ResponseBody> response2 = productService
                .getProducts()
                .execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is( true));
        System.err.println("The list of all Products have been got");

        // GET Product by Id request (Positive)
        Response<Product> response3 = productService
                .getProductById(id)
                .execute();
        assertThat(response3.isSuccessful(), CoreMatchers.is( true));
        assertThat(response3.body().getId(), equalTo(id));
        assertThat(response3.body().getTitle(), equalTo(productName));
        assertThat(response3.body().getPrice(), equalTo(productPrice));
        assertThat(response3.body().getCategoryTitle(), equalTo("Food"));
        // SQL версия асссертов
        productsExample.createCriteria()
                .andIdEqualTo((long) id)
                .andPriceEqualTo(productPrice)
                .andTitleEqualTo(productName)
                .andCategory_idEqualTo(1l);
        sqlSession.commit();
        System.err.println("All details about Product with Id-" + id + " have been checked in DB");
        System.err.println("All details about Product with Id-" + id + " have been got\n");

        // GET Product by Id request (Negative)
        Response<Product> response4 = productService
                .getProductById(5000)
                .execute();
        assertThat(response4.isSuccessful(), CoreMatchers.is( false));
        // SQL версия асссертов
        productsExample.createCriteria()
                .andIdNotEqualTo((long) 5000L)
                .andCategory_idEqualTo(1l);
        sqlSession.commit();
        System.err.println("All details about Product with Id-5000 have been checked in DB");
        System.err.println("The Product with id: 5000 doesn't exist\n");

        // PUT Modify Product request (Positive)
        productUpd = new Product()
                .withId(id)
                .withTitle(productName)
                .withCategoryTitle("Food")
                .withPrice(555); // измененное поле
        Response<Product> response5 = productService
                .modifyProduct(productUpd)
                .execute();
        assertThat(response5.isSuccessful(), CoreMatchers.is( true));
        assertThat(response5.body().getPrice(), equalTo(555));
        assertThat(response5.body().getId(), equalTo(id));
        assertThat(response5.body().getTitle(), equalTo(productName));
        assertThat(response5.body().getCategoryTitle(), equalTo("Food"));
        // SQL версия асссертов
        productsExample.createCriteria()
                .andIdEqualTo((long) id)
                .andPriceEqualTo(555)
                .andTitleEqualTo(productName)
                .andCategory_idEqualTo(1l);
        sqlSession.commit();
        System.err.println("All details about updated Product with Id-" + id + " have been checked in DB");
        System.err.println("The Product with Id-" + id + " have the new price 555\n");

        // PUT Modify Product request (Negative)
        productUpd = new Product()
                .withId(777)
                .withTitle(productName)
                .withCategoryTitle("Food")
                .withPrice(555); // измененное поле
        Response<Product> response6 = productService
                .modifyProduct(productUpd)
                .execute();
        assertThat(response6.isSuccessful(), CoreMatchers.is( false));
        // SQL версия асссертов
        productsExample.createCriteria()
                .andIdNotEqualTo((long) 777L)
                .andCategory_idEqualTo(1l);
        sqlSession.commit();
        System.err.println("All details about Product with Id-777 have been checked in DB");
        System.err.println("Product with id: 777 doesn't exist\n");
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService
                // DELETE Request
                .deleteProduct(id)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        // SQL версия асссертов
        productsExample.createCriteria()
                .andIdNotEqualTo((long) id)
                .andCategory_idEqualTo(1l);
        sqlSession.commit();
        System.err.println("The Product with Id-" + id + " haven't been detected in DB");
        System.err.println("The Product with Id-" + id + " have been deleted");
        sqlSession.close();
    }


}
