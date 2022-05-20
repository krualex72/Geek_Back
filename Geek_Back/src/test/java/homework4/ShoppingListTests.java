package homework4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListTests {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "773a4ccd79d042b68b62e9f1378df09a";
    private final String baseUrl = "https://api.spoonacular.com/mealplanner/krualex/shopping-list/";
    private final String hash = "26ce24f54f088f7ff094d66363868e5f1391e86c";
    private Integer id;

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("hash", hash)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void addProductToShoppingList() {
        // Добавление товара в Список Покупок
        AddShopListRequest addShopListRequest = new AddShopListRequest("1 package baking powder","Baking", true);
        AddShopListResponse response = given()
                .spec(requestSpecification)
                .body(addShopListRequest)
                .when()
                .post(baseUrl+"items/")
                .prettyPeek()
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(AddShopListResponse. class);
        assertThat(response.getName(), containsString("baking powder"));
        assertThat(response.getId(), notNullValue());
        assertThat(response.getIngredientId(), equalTo(18369));
        assertThat(response.getAisle(), equalTo("Baking"));
        id = response.getId();
        System.err.println("The product has been added to the shopping list with ID No " + id + "\n");

        // Проверка того, что добавленный товар находится в Списке Покупок
        GetShopListResponse responseGet = given()
                .spec(requestSpecification)
                .when()
                .get(baseUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(GetShopListResponse.class);
        // следующий ассерт - несколько часов моей жизни ;о)))
        assertThat((responseGet.getAisles().get(0).getItems().stream().filter(x-> x.getId().equals(id)).count()), equalTo(1L));
        System.err.println("The product (ID-" + id + ") is in the shopping list!\n");

        // удаление продукта из Списка Покупок
        given()
                .spec(requestSpecification)
                .when()
                .delete(baseUrl + "items/" + id)
                .then()
                .spec(responseSpecification);
        System.err.println("The product (ID-" + id + ") has been deleted from the shopping list!");

    }

    @Test
    void manuallyIdDelete(){ // делал для себя - К ДЗ НЕ ОТНОСИТСЯ!!! - но один раз сработает правильно :о)))
        // удаление продукта из Списка Покупок вручную (для удаления неудачных экспериментов)
        Integer id=1081579;
        given()
                .spec(requestSpecification)
                .when()
                .delete(baseUrl + "items/" + id)
                .then()
                .spec(responseSpecification);
        System.err.println("The product (ID-" + id + ") has been manually deleted from the shopping list!");
    }

}
