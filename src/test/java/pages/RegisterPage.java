package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.cssSelector("input[name='name']:not([disabled])"); // поле для имени
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input"); // Исправленный локатор для email
    private By passwordField = By.cssSelector("input[type='password']:not([disabled])");
    private By registerButton = By.cssSelector("button.button_button_type_primary__1O7Bx");
    private By errorMessage = By.cssSelector("p.input__error.text_type_main-default");
    private By loginLink = By.xpath("//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Открытие страницы регистрации
    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Регистрация нового пользователя с именем {name}, email {email}, и паролем {password}")
    public void register(String name, String email, String password) {
        // Ожидание видимости поля "Имя" и ввод данных
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameElement.sendKeys(name);

        // Ожидание видимости поля "Email" и ввод данных
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.sendKeys(email);  // Вводим email

        // Ожидание видимости поля "Пароль" и ввод данных
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.sendKeys(password);

        // Ожидание видимости кнопки и клик по кнопке
        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerBtn.click();
    }



    // Ожидание результата регистрации (редирект или ошибка)
    @Step("Ожидание результата регистрации (редирект или ошибка)")
    public void waitForRegistrationResult() {
        WebDriverWait resultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            resultWait.until(driver ->
                    driver.getCurrentUrl().contains("/login") ||
                            driver.findElements(errorMessage).size() > 0
            );
        } catch (Exception ignored) {}
    }

    // Проверка успешной регистрации
    @Step("Проверка успешной регистрации")
    public boolean isRegistrationSuccessful() {
        return driver.getCurrentUrl().contains("/login");
    }

    // Проверка видимости ошибки пароля
    @Step("Проверка видимости ошибки пароля")
    public boolean isPasswordErrorVisible() {
        return driver.findElements(errorMessage).size() > 0;
    }

    // Переход на страницу входа
    @Step("Переход на страницу входа")
    public void goToLoginPage() {
        WebElement loginLinkElement = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLinkElement.click();
    }
}
