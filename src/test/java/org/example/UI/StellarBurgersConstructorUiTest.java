package org.example.UI;

import org.example.sorce.UI.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class StellarBurgersConstructorUiTest extends BaseUiTest {

    public StellarBurgersConstructorUiTest(BaseUiTest.Browser browser) {
        super(browser);
    }

    @Test
    public void testSwitchToSaucesTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickSaucesTab();
        Assert.assertTrue("Вкладка 'Соусы' не стала активной", mainPage.isTabActive("Соусы"));
    }

    @Test
    public void testSwitchToFillingsTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickFillingsTab();
        Assert.assertTrue("Вкладка 'Начинки' не стала активной", mainPage.isTabActive("Начинки"));
    }

    @Test
    public void testSwitchToBunsTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        Assert.assertTrue("Вкладка 'Булки' не стала активной", mainPage.isTabActive("Булки"));
    }
}