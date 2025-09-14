package praktikum.test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;
import net.datafaker.Faker;

import static org.junit.Assert.assertTrue;
@DisplayName("Регистрация пользователя")
public class RegistrationTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    @Description("Тестирование успешной регистрации с валидными данными")
    @DisplayName("Успешная регистрация с валидными данными")
    public void perfectRegistrationTest() throws InterruptedException{
        WebDriver driver = driverFactory.getDriver();
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        String name = faker.name().fullName();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.nameInput(name);
        registrationPage.emailInput(email);
        registrationPage.passwordInput(password);
        registrationPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем перенаправление на страницу Авторизации", loginPage.loginPageLoaded());

    }
    @Test
    @Description("Тестирование регистрации с некорректным паролем")
    @DisplayName("Регистрация с некорректным паролем")
    public void registrationInvalidPassword() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String name = faker.name().fullName();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.nameInput(name);
        registrationPage.emailInput(email);
        registrationPage.passwordInput("qazx1");
        registrationPage.clickRegisterButton();
        registrationPage.checkErrorRegister();
        Assert.assertTrue("Ожидаемая ошибка не появилась", registrationPage.checkErrorRegister());
    }

}
