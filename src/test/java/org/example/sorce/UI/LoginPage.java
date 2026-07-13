package org.example.sorce.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static final String URL = "https://stellarburgers.education-services.ru/login";

    private final SelenideElement loginHeader = $(By.xpath("//h2[contains(text(), 'Вход')]"));
    private final SelenideElement emailField = $(By.xpath("//div[label[text()='Email']]//input"));
    private final SelenideElement passwordField = $(By.xpath("//div[label[text()='Пароль']]//input[@type='password']"));
    private final SelenideElement signInButton = $(By.xpath("//button[text()='Войти']"));

    @Step("Авторизация пользователя с Email: {email}")
    public void login(String email, String password) {
        emailField.shouldBe(Condition.visible).setValue(email);
        passwordField.shouldBe(Condition.visible).setValue(password);
        signInButton.shouldBe(Condition.visible).click();
    }

    @Step("Ожидание исчезновения заголовка 'Вход'")
    public void waitForLoginHeaderToDisappear() {
        loginHeader.shouldNotBe(Condition.visible);
    }

    @Step("Проверка отображения заголовка 'Вход'")
    public boolean isLoginHeaderVisible() {
        return loginHeader.shouldBe(Condition.visible).isDisplayed();
    }
}