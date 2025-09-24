package tests;

import config.TestBase;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

public class ConstructorTest extends TestBase {

    @Test
    @Description("Переход в раздел 'Булки'")
    public void testBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToBuns();
        Assert.assertTrue("Раздел 'Булки' не активен", mainPage.isSectionActive("Булки"));
    }

    @Test
    @Description("Переход в раздел 'Соусы'")
    public void testSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToSauces();
        Assert.assertTrue("Раздел 'Соусы' не активен", mainPage.isSectionActive("Соусы"));
    }

    @Test
    @Description("Переход в раздел 'Начинки'")
    public void testFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToFillings();
        Assert.assertTrue("Раздел 'Начинки' не активен", mainPage.isSectionActive("Начинки"));
    }
}
