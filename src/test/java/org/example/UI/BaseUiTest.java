package org.example.UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.sorce.ruto.User;
import org.example.sorce.ruto.UserClient;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class BaseUiTest {

    public enum Browser {
        CHROME, YANDEX
    }

    protected final UserClient userClient = new UserClient();
    protected final Browser browser;
    protected String accessToken;


    public BaseUiTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getBrowsers() {
        return Arrays.asList(new Object[][] {
                {Browser.CHROME},
                {Browser.YANDEX}
        });
    }


    @Step
    protected User createAndRegisterUniqueUser() {
        String cleanId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        String uniqueEmail = "user_" + cleanId + "@yandex.ru";
        String uniquePassword = "burgerpass" + cleanId;
        String uniqueName = "Alex_" + cleanId;

        User testUser = new User(uniqueEmail, uniquePassword, uniqueName);
        ValidatableResponse response = userClient.register(testUser);

        if (response != null) {
            String rawToken = response.extract().path("accessToken");
            if (rawToken != null) {
                this.accessToken = rawToken.replace("Bearer ", "").trim();
            }
        }
        return testUser;
    }

    @Before
    public void setUp() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        if (Browser.YANDEX.equals(browser)) {
            String userHome = System.getProperty("user.home");
            String s = File.separator;
            String yandexBinaryPath = userHome + s + "AppData" + s + "Local" + s + "Yandex" + s + "YandexBrowser" + s + "Application" + s + "browser.exe";

            File file = new File(yandexBinaryPath);
            if (!file.exists()) {
                yandexBinaryPath = "C:" + s + "Program Files" + s + "Yandex" + s + "YandexBrowser" + s + "Application" + s + "browser.exe";
            }

            options.setBinary(yandexBinaryPath);

            WebDriverManager.chromedriver().driverVersion("148.0.7778.0").setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }

        Configuration.browserCapabilities = options;
    }

    @After
    public void tearDown() {
        try {
            if (accessToken != null) {
                userClient.delete(accessToken);
            }
        } catch (Exception e) {
            System.err.println("Не удалось удалить пользователя: " + e.getMessage());
        } finally {
            accessToken = null;
            WebDriverRunner.closeWebDriver();
        }
    }
}