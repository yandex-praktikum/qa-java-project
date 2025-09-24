package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriverException;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver = null;

        // Проверка типа браузера и настройка соответствующего драйвера
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "yandex":
                driver = createYandexDriver();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        return driver;
    }

    private static WebDriver createChromeDriver() {
        // Автоматическая настройка ChromeDriver через WebDriverManager
        try {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации ChromeDriver. Проверьте настройки WebDriverManager.", e);
        }
    }

    private static WebDriver createYandexDriver() {
        try {
            // Получаем путь до Яндекс.Браузера из переменной окружения
            String yandexBinaryPath = System.getenv("YANDEX_BROWSER_PATH");

            if (yandexBinaryPath == null) {
                throw new RuntimeException("Не установлен путь к Яндекс.Браузеру. Установите переменную окружения YANDEX_BROWSER_PATH.");
            }

            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBinaryPath);

            // Используем WebDriverManager для настройки ChromeDriver
            WebDriverManager.chromedriver().setup();

            return new ChromeDriver(options);
        } catch (WebDriverException e) {
            throw new RuntimeException("Ошибка при инициализации Яндекс.Браузера. Проверьте путь к драйверу и настройки браузера.", e);
        }
    }
}
