package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.config.UserData;
import org.example.models.User;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Тесты на создание пользователя")
public class UserTests extends BaseTest {

    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Проверка успешного создания пользователя с уникальными данными")
    public void createUniqueUserSuccessTest() {
        Response response = userClient.createUser(testUser);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(testUser.getEmail().toLowerCase()))
                .body("user.name", equalTo(testUser.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

        // Получаем токен для последующего удаления пользователя
        accessToken = authClient.getAccessToken(testUser.getEmail(), testUser.getPassword());
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    @Description("Проверка ошибки при попытке создать пользователя с уже существующим email")
    public void createDuplicateUserErrorTest() {
        // Сначала создаем пользователя
        Response createResponse = userClient.createUser(testUser);
        createResponse.then().assertThat().statusCode(200);

        // Получаем токен для последующего удаления
        accessToken = authClient.getAccessToken(testUser.getEmail(), testUser.getPassword());

        // Пытаемся создать пользователя с тем же email
        User duplicateUser = new User(testUser.getEmail(), "differentPassword", "DifferentName");
        Response response = userClient.createUser(duplicateUser);

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Создание пользователя без пароля")
    @Description("Проверка ошибки при создании пользователя без пароля")
    public void createUserWithoutPasswordTest() {
        User userWithoutPassword = new User(
                UserData.generateUniqueEmail(),
                "",
                UserData.generateUniqueName()
        );

        Response response = userClient.createUser(userWithoutPassword);

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя без имени")
    @Description("Проверка ошибки при создании пользователя без имени")
    public void createUserWithoutNameTest() {
        User userWithoutName = new User(
                UserData.generateUniqueEmail(),
                UserData.DEFAULT_PASSWORD,
                ""
        );

        Response response = userClient.createUser(userWithoutName);

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя без email")
    @Description("Проверка ошибки при создании пользователя без email")
    public void createUserWithoutEmailTest() {
        User userWithoutEmail = new User(
                "",
                UserData.DEFAULT_PASSWORD,
                UserData.generateUniqueName()
        );

        Response response = userClient.createUser(userWithoutEmail);

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}