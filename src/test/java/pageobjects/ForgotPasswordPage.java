package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    public static final String FORGOT_PASSWORD_URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Поле "Email"
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");

    // Кнопка "Восстановить"
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    // Ссылка "Войти" на форме восстановления пароля
    private final By loginLink = By.linkText("Войти");

    public void clickLoginLink() {

        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
