package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By linkRepairPasswordPage = By.cssSelector("div > p:nth-child(2) > a");
    private final By email = By.cssSelector("fieldset:nth-child(1) > div > div > input");
    private final By password = By.cssSelector("fieldset:nth-child(2) > div > div > input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By loginLink = By.xpath("//a[contains(@class, 'Auth_link') and @href='/login' and text()='Войти']");
    private final By loginPage = By.xpath("//button[contains(@class, 'button_button_type_primary') and text()='Войти']");
    private final By lastLocate = By.tagName("script");


    @Step("Ввести email: {emailField}")
    public void emailInput(String emailField){
        driver.findElement(email).sendKeys(emailField);
    }
    @Step("Ввести пароль: {passwordField}")
    public void passwordInput(String passwordField){
        driver.findElement(password).sendKeys(passwordField);
    }
    @Step("Нажать кнопку Войти")
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
    @Step("Перейти на страницу восстановления пароля")
    public void linkRepairPasswordPageClick(){
        driver.findElement(linkRepairPasswordPage).click();
    }
    @Step("Перейти на страницу логина из другой страницы")
    public void loginLinkClick(){
        driver.findElement(loginLink).click();
    }
    @Step("Проверить, что страница логина загружена")
    public boolean loginPageLoaded() {
        return driver.findElement(loginPage).isDisplayed();
    }

    @Step("Открыть страницу авторизации")
    public void openLoginPage(){
        driver.get(Config.LOGIN_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }

}



