// ---- UserLoginTests.java ----
package praktikum.api;

import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import praktikum.api.config.TestBase;
import praktikum.api.model.LoginRequest;
import praktikum.api.model.RegisterRequest;
import praktikum.api.steps.UserSteps;
import praktikum.api.util.TestData;

import static org.hamcrest.Matchers.*;

public class UserLoginTests extends TestBase {

    private final UserSteps userSteps = new UserSteps(api);
    private String email;
    private String password;

    @Before
    public void setUpUser() {
        needUser = true; // чтобы TestBase знал, что надо будет удалить пользователя после теста
        email = TestData.uniqueEmail();
        password = TestData.strongPassword();

        userSteps.registerUser(new RegisterRequest(email, password, TestData.randomName()))
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Успешный логин с правильными данными")
    @Description("Регистрация нового пользователя и успешная авторизация через API. Проверяем наличие токена и success=true.")
    public void loginSuccess() {
        userSteps.loginUser(new LoginRequest(email, password))
                .then().statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Попытка логина с неверным паролем")
    @Description("Попытка авторизации пользователя с правильным email и неверным паролем. Ожидаем 401 и соответствующее сообщение об ошибке.")
    public void loginWithWrongPassword() {
        userSteps.loginUser(new LoginRequest(email, "wrongPass"))
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Попытка логина с неверным email")
    @Description("Попытка авторизации пользователя с неверным email и правильным паролем. Ожидаем 401 и сообщение об ошибке.")
    public void loginWithWrongEmail() {
        userSteps.loginUser(new LoginRequest("wrong" + email, password))
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
