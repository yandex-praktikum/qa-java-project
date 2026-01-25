package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import helpers.UserFactory;
import models.User;
import pageobjects.ForgotPasswordPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;

@Epic("Аутентификация")
@Feature("Авторизация пользователя")
public class LoginPageTest extends BaseTest {

    private User user;
    private String accessToken;
    private final UserSteps userSteps = new UserSteps();

    @Before
    public void createUser() {
        user = UserFactory.createRandomUser();
        userSteps.register(user)
                .then().statusCode(SC_OK);
        accessToken = userSteps.getAccessToken(user);

    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.delete(accessToken);
        }
        super.tearDown();
    }


    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главное странице")
    @Story("Вход с главной страницы")
    @Description("Осуществлятся успешный вход в аккаунт через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {

        driver.get(MainPage.MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Ожидался успешный вход на главную страницу", loginPage.isLoginSuccessful());
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет' на главной странице")
    @Story("Вход через 'Личный кабинет'")
    @Description("Осуществлятся успешный вход в аккаунт через кнопку 'Личный кабинет' на главной странице")
    public void loginFromProfileButtonTest() {

        driver.get(MainPage.MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Ожидался успешный вход через 'Личный кабинет'", loginPage.isLoginSuccessful());
    }

    @Test
    @DisplayName("Авторизация через форму регистрации")
    @Story("Вход с формы регистрации")
    @Description("Вход через форму регистрации")
    public void loginFromRegisterFormTest() {
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Ожидался успешный вход через форму регистрации", loginPage.isLoginSuccessful());
    }

    @Test
    @DisplayName("Авторизация через форму восстановления пароля")
    @Story("Вход со страницы восстановления пароля")
    @Description("Вход через форму восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        driver.get(ForgotPasswordPage.FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Ожидался успешный вход через форму восстановления пароля", loginPage.isLoginSuccessful());
    }

}