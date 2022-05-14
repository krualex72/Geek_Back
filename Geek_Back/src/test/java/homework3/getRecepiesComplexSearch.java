package homework3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class getRecepiesComplexSearch {

    private final String apiKey = "773a4ccd79d042b68b62e9f1378df09a";
    private final String baseUrl = "https://api.spoonacular.com/recipes/complexSearch";
    private final String maxCarb = "70";
    @Test
    void getRecipeComplexSearchWithParamsPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "salad")
                .queryParam("cuisine", "greek,french")
                .queryParam("excludeCuisine", "chinese,thai,korean")
                .queryParam("intolerances", "gluten")
                .queryParam("excludeIngredients", "riсe,flour,starch")
                .queryParam("maxReadyTime", "35")
                .queryParam("maxCarbs", "70")
                .queryParam("sort", "popularity")
                .log()
                .all()
                .when()
                .get(baseUrl)
                .prettyPeek()
                .body()
                .jsonPath();
        assertThat(response.get("results[0].id"), notNullValue()); // Response contains id
        assertThat(response.get("results[0].title"), notNullValue()); // Response contains title
        assertThat(response.get("results[0].image"), notNullValue()); // Response contains image
        assertThat(response.get("results[0].imageType"), notNullValue()); // Response contains imageType
        assertThat(response.get("results[0].nutrition"), notNullValue()); // Response contains nutrition
        assertThat(response.get("totalResults"),  is(not(equalTo(0)))); // You've got recipes
        assertThat(response.get("results[0].title"), equalTo ("Greek Yogurt Chicken Salad")); // Test title of the First recipe response
        assertThat(response.get("results[0].imageType"), equalTo ("jpg")); // Test imageType of the First recipe response
        assertThat(response.get("results[1].title"), equalTo ("Greek Side Salad")); // Test title of the Second recipe response
        assertThat(response.get("results[1].image"), equalTo ("https://spoonacular.com/recipeImages/645348-312x231.jpg")); // Test image link of the Second recipe response
        assertThat(response.get("results[0].nutrition.nutrients[0].unit"), equalTo ("g")); // Nutrients unit is 'g'
        assertThat(response.get("results[0].nutrition.nutrients[0].name"), equalTo ("Carbohydrates")); // Nutrients name is correct
        assertThat(response.get("results[0].nutrition.nutrients[0].amount"), equalTo (16.4025F)); // Nutrients value is correct
    }

    @Test
    void getRecipeComplexSearchWithParamsPositiveTest_Rice_European() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "rice")
                .queryParam("cuisine", "european")
                .queryParam("excludeCuisine", "chinese,thai,korean")
                .queryParam("excludeIngredients", "starch")
                .queryParam("maxReadyTime", "60")
                .queryParam("maxCarbs", maxCarb)
                .queryParam("maxProtein", 30)
                .queryParam("sort", "calories")
                .log()
                .all()
                .when()
                .get(baseUrl)
                .prettyPeek()
                .body()
                .jsonPath();
        assertThat(response.get("results[0].id"), notNullValue()); // Response contains id
        assertThat(response.get("results[0].title"), notNullValue()); // Response contains title
        assertThat(response.get("results[0].image"), notNullValue()); // Response contains image
        assertThat(response.get("results[0].imageType"), notNullValue()); // Response contains imageType
        assertThat(response.get("results[0].nutrition"), notNullValue()); // Response contains nutrition
        assertThat(response.get("totalResults"),  is(not(equalTo(0)))); // You've got recipes
        assertThat(response.get("results[0].title"), equalTo ("King Crab Risotto")); // Test title of the First recipe response
        assertThat(response.get("results[0].imageType"), equalTo ("jpg")); // Test imageType of the First recipe response
        assertThat(response.get("results[0].nutrition.nutrients[0].unit"), equalTo ("g")); // Nutrients unit is 'g'
        assertThat(response.get("results[0].nutrition.nutrients[0].name"), equalTo ("Protein")); // Nutrients name is correct
        assertThat(response.get("results[0].nutrition.nutrients[0].amount"), equalTo (14.2136F)); // Nutrients value is correct
    }

    @Test
    void getRecipeComplexSearchWithParamsNegativeTest_Unreal() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "noski")
                .queryParam("excludeCuisine", "chinese,thai,korean")
                .queryParam("excludeIngredients", "riсe,starch")
                .queryParam("maxReadyTime", "60")
                .queryParam("maxProtein", "30")
                .queryParam("sort", "calories")
                .log()
                .all()
                .expect()
                .body("totalResults", equalTo(0)) // You haven't got recipes
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200) // Is Status Code 200?
                .statusLine("HTTP/1.1 200 OK"); // Is Status OK?
    }

    @Test
    void getRecipeComplexSearchWithParamsPositiveTest_Empty() {
        given()
                .queryParam("apiKey", apiKey)
                .log()
                .all()
                .expect()
                .body("totalResults", equalTo(5222)) // You've got all recipes
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200) // Is Status Code 200?
                .statusLine("HTTP/1.1 200 OK"); // Is Status OK?
    }

}
