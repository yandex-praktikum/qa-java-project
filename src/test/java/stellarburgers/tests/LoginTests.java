package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;

@DisplayName("Тесты на авторизацию пользователя")
public class LoginTests extends BaseTest {

    @Test
    @DisplayName("Вход под существующим пользователем")
    @Description("Проверка успешной авторизации с правильными учетными данными")
    public void loginWithValidCredentialsTest() {
        // Сначала создаем пользователя
        Response createResponse = userClient.createUser(testUser);
        createResponse.then().assertThat().statusCode(200);

        Response response = authClient.login(testUser.getEmail(), testUser.getPassword());

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(testUser.getEmail().toLowerCase()))
                .body("user.name", equalTo(testUser.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

        // Сохраняем токен для удаления пользователя в tearDown
        accessToken = response.then().extract().path("accessToken");
        assertNotNull("Access token должен быть не null", accessToken);
    }

    @Test
    @DisplayName("Вход с неверным паролем")
    @Description("Проверка ошибки при авторизации с неправильным паролем")
    public void loginWithInvalidPasswordTest() {
        // Сначала создаем пользователя
        Response createResponse = userClient.createUser(testUser);
        createResponse.then().assertThat().statusCode(200);

        // Получаем токен для последующего удаления
        accessToken = authClient.getAccessToken(testUser.getEmail(), testUser.getPassword());

        // Пытаемся войти с неверным паролем
        Response response = authClient.login(testUser.getEmail(), "wrongPassword");

        response.then()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Вход с неверным email")
    @Description("Проверка ошибки при авторизации с неправильным email")
    public void loginWithInvalidEmailTest() {
        // Сначала создаем пользователя
        Response createResponse = userClient.createUser(testUser);
        createResponse.then().assertThat().statusCode(200);

        // Получаем токен для последующего удаления
        accessToken = authClient.getAccessToken(testUser.getEmail(), testUser.getPassword());

        // Пытаемся войти с неверным email
        Response response = authClient.login("nonexistent@yandex.ru", testUser.getPassword());

        response.then()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}