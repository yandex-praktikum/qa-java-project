package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import models.User;

import java.time.Duration;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.education-services.ru/register";

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By nameInput = By.name("name");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and contains(text(), 'Некорректный')]");
    private final By loginLink = By.linkText("Войти");

    public void fillRegisterForm(String name, String email, String password) {

        driver.findElement(nameInput).

                sendKeys(name);
        driver.findElement(emailInput).

                sendKeys(email);
        driver.findElement(passwordInput).

                sendKeys(password);
        driver.findElement(registerButton).

                click();
    }

    public boolean waitForPasswordError() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickLoginLink() {

        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void register(User user) {
        fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
    }
}