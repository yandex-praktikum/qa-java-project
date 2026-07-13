package org.example.sorce.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    public static final String URL = "https://stellarburgers.education-services.ru/register";

    private final SelenideElement nameField = $(By.xpath("//div[label[text()='Имя']]//input"));
    private final SelenideElement emailField = $(By.xpath("//div[label[text()='Email']]//input"));
    private final SelenideElement passwordField = $(By.xpath("//div[label[text()='Пароль']]//input[@type='password']"));
    private final SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private final SelenideElement loginLink = $(By.xpath("//a[contains(text(), 'Войти')]"));

    private final SelenideElement passwordError = $(By.xpath("//div[label[text()='Пароль']]/following-sibling::p[contains(@class, 'input__error')]"));

    @Step("Регистрация пользователя с именем: {name}, Email: {email}")
    public void register(String name, String email, String password) {
        nameField.shouldBe(Condition.visible).setValue(name);
        emailField.shouldBe(Condition.visible).setValue(email);
        passwordField.shouldBe(Condition.visible).setValue(password);
        registerButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик по ссылке 'Войти' на странице регистрации")
    public void clickLoginLink() {
        loginLink.shouldBe(Condition.visible).click();
    }

    @Step("Проверка отображения ошибки некорректного пароля")
    public boolean isPasswordErrorVisible() {
        return passwordError.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Получение текста ошибки валидации пароля")
    public String getPasswordErrorText() {
        return passwordError.shouldBe(Condition.visible).getText();
    }
}