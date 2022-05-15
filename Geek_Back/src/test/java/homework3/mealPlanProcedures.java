package homework3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class mealPlanProcedures {

    private final String apiKey = "773a4ccd79d042b68b62e9f1378df09a";
    private final String baseUrl = "https://api.spoonacular.com/mealplanner/krualex/items/";
    private final String hash = "26ce24f54f088f7ff094d66363868e5f1391e86c";

    private String id;

    @Test
    void addRecipeToMealPlan() {
        id = given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"date\": 1655208352,\n" // 14 JUN 2022
                        + " \"slot\": 1,\n" // обед
                        + " \"position\": 1,\n" // позиция
                        + " \"type\": \"RECIPE\",\n" // рецепт
                        + " \"value\": {\n"
                        + " \"id\": 645384,\n"
                        + " \"servings\": 1,\n"
                        + " \"title\": \"Greek Yogurt Chicken Salad\",\n"
                        + " \"imageType\": \"jpg\",\n"
                        + " }\n"
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
        System.out.println("The recipe has been added to the meal plan with ID No " + id);
    }

    @AfterEach
    void tearDown() {
        JsonPath response =given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .log()
                .all()
                .delete(baseUrl + id)
                .prettyPeek()
                //.then() // так было в примере лекции - ниже удачный и более валидный эксперимент
                //.statusCode(200)
                .body()
                .jsonPath();
        assertThat(response.get("status"), equalTo("success"));
    }
}
