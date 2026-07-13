package org.example.UI;

import com.codeborne.selenide.Selenide;
import org.example.sorce.ruto.User;
import org.example.sorce.UI.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class StellarBurgersLoginUiTest extends BaseUiTest {

    public StellarBurgersLoginUiTest(BaseUiTest.Browser browser) {
        super(browser);
    }

    @Test
    public void testLoginFromMainPageButton() {
        User user = createAndRegisterUniqueUser();

        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForLoginHeaderToDisappear();

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = Selenide.page(AccountPage.class);
        Assert.assertEquals("Имя пользователя не совпадает", user.getName(), accountPage.getNameValue());
        Assert.assertEquals("Email пользователя не совпадает", user.getEmail(), accountPage.getLoginValue());
    }

    @Test
    public void testLoginFromPersonalAccountButton() {
        User user = createAndRegisterUniqueUser();

        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForLoginHeaderToDisappear();

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = Selenide.page(AccountPage.class);
        Assert.assertEquals("Имя пользователя не совпадает", user.getName(), accountPage.getNameValue());
        Assert.assertEquals("Email пользователя не совпадает", user.getEmail(), accountPage.getLoginValue());
    }

    @Test
    public void testLoginFromRegisterPageFormButton() {
        User user = createAndRegisterUniqueUser();

        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.clickLoginLink();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForLoginHeaderToDisappear();

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = Selenide.page(AccountPage.class);
        Assert.assertEquals("Имя пользователя не совпадает", user.getName(), accountPage.getNameValue());
        Assert.assertEquals("Email пользователя не совпадает", user.getEmail(), accountPage.getLoginValue());
    }

    @Test
    public void testLoginFromForgotPasswordPageFormButton() {
        User user = createAndRegisterUniqueUser();

        ForgotPasswordPage forgotPage = open(ForgotPasswordPage.URL, ForgotPasswordPage.class);
        forgotPage.clickLoginLink();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForLoginHeaderToDisappear();

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = Selenide.page(AccountPage.class);
        Assert.assertEquals("Имя пользователя не совпадает", user.getName(), accountPage.getNameValue());
        Assert.assertEquals("Email пользователя не совпадает", user.getEmail(), accountPage.getLoginValue());
    }
}