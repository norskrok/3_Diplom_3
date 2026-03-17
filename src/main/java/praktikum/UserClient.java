package praktikum;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/auth/";

    public ValidatableResponse create(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "register")
                .then();
    }

    public void delete(String accessToken) {
        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .delete(BASE_URL + "user")
                    .then();
        }
    }
}