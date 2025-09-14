package praktikum.pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;

import java.time.Duration;


public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By name = By.xpath("//fieldset[1]/div/div/input");
    private final By email = By.xpath("//fieldset[2]/div/div/input");
    private final By password = By.xpath("//fieldset[3]/div/div/input");
    private final By registerButton = By.xpath("//button[normalize-space()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");
    private final By inputError = By.cssSelector(".input__error.text_type_main-default");
    private final By lastLocate = By.tagName("script");

    @Step("Открыть страницу регистрации")
    public void openRegisterPage(){
        driver.get(Config.REGISTER_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }
    @Step("Ввести имя: {nameField}")
    public void nameInput(String nameField) {
        driver.findElement(name).sendKeys(nameField);
    }
    @Step("Ввести email: {emailField}")
    public void emailInput(String emailField){
        driver.findElement(email).sendKeys(emailField);
    }
    @Step("Ввести пароль: {passwordField}")
    public void passwordInput(String passwordField){
        driver.findElement(password).sendKeys(passwordField);
   }
    @Step("Нажать кнопку Регистрация")
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
   }
    @Step("Перейти на страницу логина из регистрации")
    public void clickLoginLink(){
        driver.findElement(loginLink).click();
   }
    @Step("Проверить наличие ошибки при регистрации")
    public boolean checkErrorRegister() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(inputError));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
