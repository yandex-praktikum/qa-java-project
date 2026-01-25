package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverFactory {
    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("yandex")) {

            ChromeOptions options = new ChromeOptions();

            String yandexPath = "C:\\Users\\xloppok\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

            File yandexBinary = new File(yandexPath);
            if (!yandexBinary.exists()) {
                throw new RuntimeException("Yandex browser binary not found at: " + yandexPath);
            }

            options.setBinary(yandexBinary);

            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--start-maximized");

            return new ChromeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--start-maximized");

            return new ChromeDriver(options);
        }
    }
}