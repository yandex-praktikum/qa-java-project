package config;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ApiHelper;
import config.DriverFactory;

public class TestBase {
    protected WebDriver driver;
    protected String browser;
    protected String accessToken;  // Токен для удаления пользователя
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    // Конструктор для задания браузера (chrome или yandex)
    public TestBase(String browser) {
        this.browser = browser;
    }

    // Публичный конструктор без аргументов для JUnit 4
    public TestBase() {
        this("chrome");
    }

    @Before
    public void setUp() {
        // Инициализация драйвера через фабрику
        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");

        // Регистрация пользователя через API
        ApiHelper.UserWithResponse userWithResp = ApiHelper.registerNewUser();

        Response response = userWithResp.getResponse();
        if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
            accessToken = response.jsonPath().getString("accessToken");
            logger.info("Регистрация прошла успешно, токен: " + accessToken);
        } else {
            logger.error("Ошибка регистрации пользователя через API. Код: "
                    + response.getStatusCode() + " Ответ: " + response.asString());
            throw new RuntimeException("Ошибка регистрации пользователя через API. Код: "
                    + response.getStatusCode() + " Ответ: " + response.asString());
        }
    }

    @After
    public void tearDown() {
        // Удаление пользователя через API
        if (accessToken != null) {
            Response deleteResponse = ApiHelper.deleteUser(accessToken);
            if (deleteResponse.getStatusCode() == 200) {
                logger.info("Пользователь успешно удален через API.");
            } else {
                logger.error("Ошибка при удалении пользователя через API. Код: "
                        + deleteResponse.getStatusCode());
            }
        }

        // Закрытие драйвера
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                logger.error("Ошибка при закрытии драйвера: " + e.getMessage(), e);
            }
        }
    }

    // Геттер для доступа к драйверу
    public WebDriver getDriver() {
        return driver;
    }
}
