package praktikum.test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import praktikum.models.User;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;
import praktikum.test.DriverFactory;
import praktikum.utill.*;

import static org.junit.Assert.assertTrue;
@DisplayName("Вход пользователя")
public class LoginTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private String accessToken;
    private String uniqueEmail;
    private String password;

    @Before
    @Step("Подготовка: генерируем и регистрируем уникального пользователя")
    public void setUp() {
        // 1) генерируем уникального пользователя
        User user = TestDataGenerator.randomUser();
        uniqueEmail = user.getEmail();
        password = user.getPassword();

        // 2) регистрируем через API и получаем токен (если API сразу возвращает токен)
        try {
            accessToken = UserApi.registerAndGetAccessToken(user);
        } catch (Exception e) {
            // если регистрация упала — пробуем бросить исключение, чтобы тесты не запускались,
            // либо логируем и даём провалиться вызову (по вашей политике)
            throw new RuntimeException("Не удалось зарегистрировать тестового пользователя через API", e);
        }
    }

    @After
    @Step("Очистка: удаляем тестового пользователя при наличии accessToken")
    public void tearDown() {
        // 3) удаляем пользователя по accessToken
        if (accessToken == null) {
            // резерв: если токен не вернулся при регистрации — попробуем получить его через логин
            try {
                accessToken = UserApi.loginAndGetAccessToken(uniqueEmail, password);
            } catch (Exception ignored) { }
        }

        if (accessToken != null) {
            try {
                UserApi.deleteUser(accessToken);
            } catch (Exception e) {
                System.err.println("Не удалось удалить тестового пользователя: " + e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Вход из личного кабинета")
    @Description("Проверяем, что пользователь может войти через кнопку личного кабинета")
    public void loginFromPersonalAccountTest()  {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.personalAccountButtonPageClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(uniqueEmail);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();

        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }

    @Test
    @DisplayName("Вход с главной страницы")
    @Description("Пользователь заходит через кнопку 'Войти' на главной странице")
    public void loginFromMainButtonTest()  {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.loginInMainPageClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(uniqueEmail);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();

        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    @Description("Открываем форму восстановления пароля, переходим обратно на вход и авторизуемся")
    public void loginFromForgotPasswordFormTest() {
        WebDriver driver = driverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.linkRepairPasswordPageClick();
        loginPage.loginLinkClick();

        loginPage.emailInput(uniqueEmail);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();

        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    @Description("Переходим из регистрации на страницу входа и авторизуемся")
    public void loginFromRegistrationForm() {
        WebDriver driver = driverFactory.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(uniqueEmail);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();

        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }
}