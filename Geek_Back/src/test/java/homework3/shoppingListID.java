package homework3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class shoppingListID {

    private final String apiKey = "773a4ccd79d042b68b62e9f1378df09a";
    private final String baseUrl = "https://api.spoonacular.com/mealplanner/krualex/shopping-list/items/";
    private final String hash = "26ce24f54f088f7ff094d66363868e5f1391e86c";

    private String id;

    @Test
    void ProductAndShoppingList() { // добавление продукта в корзину
        id = given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"item\": \"1 package baking powder\",\n"
                        + " \"aisle\": \"Baking\",\n" //
                        + " \"parse\": true,\n"
                        + "}")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        System.out.println("The product has been added to the shopping list with ID No " + id);

        JsonPath response = given() // проверка, что продкут в корзине
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/mealplanner/krualex/shopping-list")
                .prettyPeek()
                .body()
                .jsonPath();
        assertThat(response.get("aisles[0].items[1].id"), equalTo(Integer.parseInt(id))); // проверяем, что товар с ID в листе
        System.out.println("The product (ID-" + id + ") is in the shopping list!");

        JsonPath response2 = given() // удаление продукта из корзины
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .log()
                .all()
                .delete(baseUrl + id)
                .prettyPeek()
                .body()
                .jsonPath();
        assertThat(response2.get("status"), equalTo("success"));
        System.out.println("The product (ID-" + id + ") has been deleted from the shopping list!");
    }
}

