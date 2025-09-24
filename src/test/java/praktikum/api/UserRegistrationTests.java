package praktikum.api;

import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import praktikum.api.config.TestBase;
import praktikum.api.model.RegisterRequest;
import praktikum.api.steps.UserSteps;
import praktikum.api.util.TestData;

import static org.hamcrest.Matchers.*;

public class UserRegistrationTests extends TestBase {

    private final UserSteps userSteps = new UserSteps(api);
    private String email;
    private String password;
    private String name;

    @Before
    public void generateUserData() {
        // генерируем новые данные для каждого теста
        email = TestData.uniqueEmail();
        password = TestData.strongPassword();
        name = TestData.randomName();
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Создание пользователя через API с валидными данными. Проверка success=true и корректного email.")
    public void registerSuccessful() {
        var reg = new RegisterRequest(email, password, name);
        userSteps.registerUser(reg)
                .then().statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email));
    }

    @Test
    @DisplayName("Попытка регистрации существующего пользователя")
    @Description("Создаем пользователя и пытаемся зарегистрировать того же пользователя повторно. Ожидаем 403 и соответствующее сообщение.")
    public void registerUserAlreadyExists() {
        var reg = new RegisterRequest(email, password, name);

        // первая регистрация
        userSteps.registerUser(reg).then().statusCode(200);

        // повторная с теми же данными
        userSteps.registerUser(reg)
                .then().statusCode(403)
                .body("success", equalTo(false))
                .body("message", containsString("User already exists"));
    }

    @Test
    @DisplayName("Регистрация без email")
    @Description("Попытка регистрации пользователя без email. Ожидаем 403 и сообщение об обязательных полях.")
    public void registerWithoutEmail() {
        userSteps.registerUser(new RegisterRequest("", password, name))
                .then().statusCode(403)
                .body("success", equalTo(false))
                .body("message", containsString("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Регистрация без пароля")
    @Description("Попытка регистрации пользователя без пароля. Проверка ошибки 403 и сообщения об обязательных полях.")
    public void registerWithoutPassword() {
        userSteps.registerUser(new RegisterRequest(email, "", name))
                .then().statusCode(403)
                .body("success", equalTo(false))
                .body("message", containsString("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Регистрация без имени")
    @Description("Попытка регистрации пользователя без имени. Проверка ошибки 403 и сообщения об обязательных полях.")
    public void registerWithoutName() {
        userSteps.registerUser(new RegisterRequest(email, password, ""))
                .then().statusCode(403)
                .body("success", equalTo(false))
                .body("message", containsString("Email, password and name are required fields"));
    }
}
