package org.example.UI;

import com.codeborne.selenide.Selenide;
import io.restassured.response.ValidatableResponse;
import org.example.sorce.ruto.User;
import org.example.sorce.UI.LoginPage;
import org.example.sorce.UI.RegisterPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StellarBurgersRegisterUiTest extends BaseUiTest {

    public StellarBurgersRegisterUiTest(BaseUiTest.Browser browser) {
        super(browser);
    }

    @Test
    public void testSuccessfulRegistration() {
        String cleanId = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);

        String regName = "RegUser" + cleanId;
        String newUiEmail = "ui_perfect_" + cleanId + "@yandex.ru";
        String regPassword = "burgerpass" + cleanId;

        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.register(regName, newUiEmail, regPassword);

        LoginPage loginPage = Selenide.page(LoginPage.class);
        assertTrue("Не перешли на страницу логина после успешной регистрации", loginPage.isLoginHeaderVisible());

        User userCredentials = new User(newUiEmail, regPassword, null);
        ValidatableResponse response = userClient.login(userCredentials);

        if (response != null && response.extract().statusCode() == 200) {
            String rawToken = response.extract().path("accessToken");
            if (rawToken != null) {
                this.accessToken = rawToken.replace("Bearer ", "").trim();
            }
        }
    }

    @Test
    public void testRegistrationWithShortPasswordThrowsError() {
        String cleanId = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        String failEmail = "ui_fail_" + cleanId + "@yandex.ru";
        String shortPassword = "123";

        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.register("FailUser", failEmail, shortPassword);

        assertTrue("Сообщение об ошибке некорректного пароля не отображается", registerPage.isPasswordErrorVisible());
        assertEquals("Текст ошибки не совпадает с ТЗ", "Некорректный пароль", registerPage.getPasswordErrorText());
    }
}