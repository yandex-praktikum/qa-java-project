package praktikum.test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

@DisplayName("Проверка перехода по вкладкам")
public class ConstructorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    @DisplayName("Переход на вкладку «Булки» — элемент в видимой области")
    @Description("Открываем главную страницу, переключаем вкладки (Sauce → Breads), ожидаем, что якорь булок видим.")
    public void constructorTabBreadTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceTab();
        mainPage.clickBreadsTab();
        mainPage.waitForBreadsAnchorInViewport();
        mainPage.findBreadsAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isBreadsAnchorInViewport());

    }
    @Test
    @DisplayName("Переход на вкладку «Соусы» — элемент в видимой области")
    @Description("Открываем главную страницу, переключаем на вкладку Sauce и проверяем видимость якоря соусов.")
    public void constructorTabSauceTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceTab();
        mainPage.waitForSauceAnchorInViewport();
        mainPage.findSauceAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isSauceAnchorInViewport());


    }
    @Test
    @DisplayName("Переход на вкладку «Топпинги» — элемент в видимой области")
    @Description("Открываем главную страницу, переключаем на вкладку Toppings и проверяем видимость якоря топпингов.")
    public void constructorTabToppingsTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickToppingsTab();
        mainPage.waitForToppingsAnchorInViewport();
        mainPage.findToppingsAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isToppingsAnchorInViewport());


    }
}
