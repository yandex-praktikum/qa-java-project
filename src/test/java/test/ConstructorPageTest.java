package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.ConstructorPage;

@Epic("Конструктор бургера")
@Feature("Переход по вкладкам ингредиентов")
public class ConstructorPageTest extends BaseTest {

    private ConstructorPage constructorPage;

    @Before
    public void openConstructorPage() {
        driver.get(ConstructorPage.MAIN_PAGE_URL);
        constructorPage = new ConstructorPage(driver);

    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Происходит успешный переход к разделу 'Соусы'")
    public void switchToSaucesTabTest() {
        constructorPage.clickSaucesTab();
        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Соусы", activeTab);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Происходит успешный переход к разделу 'Начинки'")
    public void switchToFillingsTabTest() {
        constructorPage.clickFillingsTab();
        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Начинки", activeTab);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Сначала происходит переход к разделу 'Соусы', затем происходит переход к разделу 'Булки'")
    public void switchToBunsTabTest() {
        constructorPage.clickSaucesTab();
        constructorPage.waitForActiveTab("Соусы");
        constructorPage.clickBunsTab();
        constructorPage.waitForActiveTab("Булки");
        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Булки", activeTab);
    }
}
