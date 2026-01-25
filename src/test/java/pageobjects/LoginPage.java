package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.education-services.ru/login";

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Поле "email"
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");

    //Поле "Пароль"
    private final By passwordInput = By.name("Пароль");

    //Кнопка "Войти"
    private final By loginButton = By.xpath("//button[text()='Войти']");

    //Ссылка "Восстановить пароль"
    private final By forgotPasswordLink = By.linkText("Восстановить пароль");


    public void login(String email, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).

                sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).

                sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).

                click();
    }

    public boolean isLoginSuccessful() {

        return wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

    }

    public void clickForgotPasswordLink() {

        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }
}