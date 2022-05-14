package homework3;

import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class postRecipesCuisine {

    private final String apiKey = "773a4ccd79d042b68b62e9f1378df09a";
    private final String baseUrl = "https://api.spoonacular.com/recipes/cuisine";

    @Test
    void ClassifyCuisinePositiveTest_PannaCotta() {

        String response = given()
                .queryParam("apiKey", apiKey)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .formParam("title", "Panna Cotta")
                .formParam("ingredientList", "Raspberry")
                .formParam("language", "en")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines[0]", equalTo("Mediterranean"))
                .body("cuisines[1]", equalTo("European"))
                .body("cuisines[2]", equalTo("Italian"))
                .body("confidence", equalTo(0.85F))
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

    @Test
    void ClassifyCuisinePositiveTest_Colcannon() {

        String response = given()
                .queryParam("apiKey", apiKey)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .formParam("title", "colcannon")
                .formParam("ingredientList", "potato")
                .formParam("language", "en")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("cuisine", equalTo("European"))
                .body("cuisines[0]", equalTo("European"))
                .body("cuisines[1]", equalTo("Irish"))
                .body("confidence", equalTo(0.95F))
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

    @Test
    void ClassifyCuisinePositiveTest_Masala() {

        String response = given()
                .queryParam("apiKey", apiKey)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .formParam("title", "masala")
                .formParam("ingredientList", "water")
                .formParam("language", "en")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("cuisine", equalTo("Indian"))
                .body("cuisines[0]", equalTo("Indian"))
                .body("cuisines[1]", equalTo("Asian"))
                .body("confidence", equalTo(0.85F))
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

    @Test
    void ClassifyCuisinePositiveTest_Kimchi() {

        String response = given()
                .queryParam("apiKey", apiKey)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .formParam("title", "kimchi")
                .formParam("ingredientList", "potato")
                .formParam("language", "en")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("cuisine", equalTo("Korean"))
                .body("cuisines[0]", equalTo("Korean"))
                .body("cuisines[1]", equalTo("Asian"))
                .body("confidence", equalTo(0.85F))
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

    @Test
    void ClassifyCuisineNegativeTest_Unreal() {

        String response = given()
                .queryParam("apiKey", apiKey)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .formParam("title", "Noski")
                .formParam("ingredientList", "potato")
                .formParam("language", "en")
                .log()
                .all()
                .when()
                .post(baseUrl)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines[0]", equalTo("Mediterranean"))
                .body("cuisines[1]", equalTo("European"))
                .body("cuisines[2]", equalTo("Italian"))
                .body("confidence", equalTo(0.0F))
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }
}