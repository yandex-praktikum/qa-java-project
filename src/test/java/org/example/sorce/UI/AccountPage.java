package org.example.sorce.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class AccountPage {

    public static final String URL = "https://stellarburgers.education-services.ru/account/profile";

    private final SelenideElement nameInput = $(By.xpath("//label[text()='Имя']/following-sibling::input"));
    private final SelenideElement loginInput = $(By.xpath("//label[text()='Логин']/following-sibling::input"));

    public void verifyProfileData(String expectedName, String expectedEmail) {
        assertEquals("Имя пользователя не совпадает", expectedName, getNameValue());
        assertEquals("Email пользователя не совпадает", expectedEmail, getLoginValue());
    }

    public String getNameValue() {
        return nameInput.shouldBe(Condition.visible).getValue();
    }

    public String getLoginValue() {
        return loginInput.shouldBe(Condition.visible).getValue();
    }
}
