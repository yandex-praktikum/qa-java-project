package tests;

import config.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.ApiHelper;
import utils.ApiHelper.User;
import utils.ApiHelper.UserWithResponse;

public class LoginTest extends TestBase {

    private User testUser; // пользователь для всех тестов

    @Before
    public void createUser() {
        // Генерация и регистрация пользователя через API
        UserWithResponse userWithResponse = ApiHelper.registerNewUser();
        testUser = userWithResponse.getUser();

        // Сохраняем токен для последующего удаления в TestBase
        String accessToken = userWithResponse.getResponse().jsonPath().getString("accessToken");
        if (accessToken != null) {
            this.accessToken = accessToken;
        }
    }

    @Test
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful());
        Assert.assertFalse(loginPage.isErrorMessageVisible());
    }

    @Test
    @Description("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickConstructor();
        loginPage.clickPersonalCabinet();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful());
        Assert.assertFalse(loginPage.isErrorMessageVisible());
    }

    @Test
    @Description("Вход через форму регистрации")
    public void testLoginFromRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful());
        Assert.assertFalse(loginPage.isErrorMessageVisible());
    }

    @Test
    @Description("Вход через форму восстановления пароля")
    public void testLoginFromRecoverPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecover();
        loginPage.clickRecoverLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful());
        Assert.assertFalse(loginPage.isErrorMessageVisible());
    }
}
