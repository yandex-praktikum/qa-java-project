package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.TestData;

public class ApiHelper {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth/";
    private static final Logger logger = LoggerFactory.getLogger(ApiHelper.class);

    // Регистрация пользователя с передачей объекта (RestAssured сам сериализует User в JSON)
    public static Response registerNewUser(User user) {
        logger.info("Регистрация пользователя: email={}, name={}", user.getEmail(), user.getName());

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(user) // RestAssured сам преобразует объект в JSON
                .post(BASE_URL + "register");

        logger.info("Response Body: " + response.getBody().asString());
        logger.info("Status Code: " + response.getStatusCode());

        return response;
    }

    // Регистрация пользователя без аргументов (с генерацией)
    public static UserWithResponse registerNewUser() {
        String email = TestData.getRandomEmail();
        String password = TestData.getRandomPassword();
        String name = TestData.getRandomName();

        User user = new User(email, password, name);
        Response response = registerNewUser(user);

        return new UserWithResponse(user, response);
    }

    // Удаление пользователя
    public static Response deleteUser(String accessToken) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .delete(BASE_URL + "user");
    }

    // Класс пользователя
    public static class User {
        private String email;
        private String password;
        private String name;

        public User(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getName() { return name; }
    }

    // Класс для возвращения пользователя вместе с Response
    public static class UserWithResponse {
        private final User user;
        private final Response response;

        public UserWithResponse(User user, Response response) {
            this.user = user;
            this.response = response;
        }

        public User getUser() { return user; }
        public Response getResponse() { return response; }
    }
}
