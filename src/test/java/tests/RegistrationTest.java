package tests;

import config.TestBase;
import data.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import pages.RegisterPage;

public class RegistrationTest extends TestBase {

    // Вспомогательный метод для регистрации пользователя и проверки успешности
    @Step("Регистрация пользователя с именем {name}, email {email}, и паролем {password}")
    private void registerUserAndCheckSuccess(String name, String email, String password) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(name, email, password);
        registerPage.waitForRegistrationResult();
        Assert.assertTrue("Регистрация не прошла", registerPage.isRegistrationSuccessful());
    }

    @Test
    @Description("Успешная регистрация нового пользователя")
    public void testSuccessfulRegistration() {
        registerUserAndCheckSuccess(
                TestData.getRandomName(),
                TestData.getRandomEmail(),
                TestData.getRandomPassword()
        );
    }

    @Test
    @Description("Ошибка при регистрации с коротким паролем")
    public void testRegistrationWithShortPassword() {
        String name = TestData.getRandomName();
        String email = TestData.getRandomEmail();
        String shortPassword = TestData.shortPassword;

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(name, email, shortPassword);
        registerPage.waitForRegistrationResult();

        Assert.assertTrue("Ожидалась ошибка 'Пароль слишком короткий'", registerPage.isPasswordErrorVisible());
    }
}
