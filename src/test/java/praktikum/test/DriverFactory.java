package praktikum.test;


import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.utill.Config;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        try {
            if ("chrome".equals(browser)) {
                startChrome();
            } else if ("yandex".equals(browser)) {
                startYandex();
            } else {
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при инициализации драйвера: " + e.getMessage());
            throw e;
        }
    }

    private void startChrome() {
        try {
            System.setProperty("selenium.manager.enable", "true");
            ChromeOptions options = new ChromeOptions();
            configureCommonOptions(options);
            driver = new ChromeDriver(options);
            configureDriverSettings();
        } catch (Exception e) {
            System.err.println("Ошибка при запуске Chrome: " + e.getMessage());
            throw e;
        }
    }

    private void startYandex() {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\new\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Mishganich\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            configureCommonOptions(options);
            driver = new ChromeDriver(options);
            configureDriverSettings();
        } catch (Exception e) {
            System.err.println("Ошибка при запуске Яндекс.Браузера: " + e.getMessage());
            throw e;
        }
    }

    private void configureCommonOptions(ChromeOptions options) {
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
    }

    private void configureDriverSettings() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        driver.manage().window().maximize();
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
