package org.example.sorce.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static final String URL = "https://stellarburgers.education-services.ru/";

    private final SelenideElement bunsTab = $(By.xpath("//span[text()='Булки']/parent::div"));
    private final SelenideElement saucesTab = $(By.xpath("//span[text()='Соусы']/parent::div"));
    private final SelenideElement fillingsTab = $(By.xpath("//span[text()='Начинки']/parent::div"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private final SelenideElement personalAccountButton = $(By.xpath("//p[text()='Личный Кабинет']/parent::a"));
    private final SelenideElement constructorHeader = $(By.xpath("//h1[text()='Соберите бургер']"));

    @Step("Переключение на вкладку 'Булки'")
    public void clickBunsTab() {
        bunsTab.shouldBe(Condition.visible).click();
    }

    @Step("Переключение на вкладку 'Соусы'")
    public void clickSaucesTab() {
        saucesTab.shouldBe(Condition.visible).click();
    }

    @Step("Переключение на вкладку 'Начинки'")
    public void clickFillingsTab() {
        fillingsTab.shouldBe(Condition.visible).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
    }

    @Step("Переход в 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        personalAccountButton.shouldBe(Condition.visible).click();
    }

    @Step("Проверка видимости заголовка конструктора 'Соберите бургер'")
    public boolean isConstructorHeaderVisible() {
        return constructorHeader.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Проверка активности вкладки '{tabName}'")
    public boolean isTabActive(String tabName) {
        return $(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[contains(text(), '" + tabName + "')]"))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }
}

