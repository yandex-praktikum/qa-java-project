package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private By passwordField = By.cssSelector("input[type='password']");
    private By loginButton = By.cssSelector("button.button_button_type_primary__1O7Bx");
    private By errorMessage = By.cssSelector("p.input__error.text_type_main-default");
    private By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private By recoverLoginButton = By.xpath("//a[text()='Войти']");
    private By personalCabinetButton = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX')]//p[text()='Личный Кабинет']");
    private By recoverButton = By.xpath("//a[text()='Восстановить пароль']");
    private By constructorButton = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX')]//p[text()='Конструктор']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ожидание поля для ввода email")
    public void waitForEmailField() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    @Step("Клик по кнопке 'Личный кабинет' через JavaScript")
    public void clickPersonalCabinet() {
        WebElement cabinetButton = wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cabinetButton);
        // Ждем появления поля email после перехода
        waitForEmailField();
    }

    @Step("Клик по кнопке 'Конструктор' через JavaScript")
    public void clickConstructor() {
        WebElement constructorBtn = wait.until(ExpectedConditions.elementToBeClickable(constructorButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", constructorBtn);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }


    @Step("Клик по кнопке 'Восстановить' через JavaScript")
    public void clickRecoverLoginButton() {
        WebElement recoverBtn = wait.until(ExpectedConditions.elementToBeClickable(recoverLoginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", recoverBtn);
        waitForEmailField();
    }

    @Step("Клик по кнопке 'Восстановить пароль' для перехода на страницу восстановления")
    public void clickRecover() {
        WebElement recoverBtn = wait.until(ExpectedConditions.elementToBeClickable(recoverButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", recoverBtn);
        waitForEmailField();
    }

    @Step("Нажатие кнопки Войти через JavaScript")
    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
    }

    @Step("Ожидание результата входа")
    public void waitForLoginResult() {
        WebDriverWait resultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        resultWait.until(driver ->
                driver.findElements(placeOrderButton).size() > 0 ||
                        driver.findElements(errorMessage).size() > 0
        );
    }

    @Step("Авторизация с email {email}")
    public void login(String email, String password) {
        waitForEmailField();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        waitForLoginResult();
    }

    @Step("Проверка успешного входа")
    public boolean isLoginSuccessful() {
        return driver.findElements(placeOrderButton).size() > 0;
    }

    @Step("Проверка видимости ошибки при неверных данных")
    public boolean isErrorMessageVisible() {
        return driver.findElements(errorMessage).size() > 0;
    }
}
