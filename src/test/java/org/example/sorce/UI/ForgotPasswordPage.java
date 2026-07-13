package org.example.sorce.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    public static final String URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final SelenideElement loginLink = $(By.xpath("//a[text()='Войти']"));

    public void clickLoginLink() {
        loginLink.shouldBe(Condition.visible).click();
    }
}