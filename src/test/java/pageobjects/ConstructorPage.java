package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.education-services.ru/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By bunsTab = By.xpath("//span[text()='Булки']/ancestor::div[contains(@class,'tab')]");

    private final By saucesTab = By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class,'tab')]");

    private final By fillingsTab = By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class,'tab')]");

    private final By activeTab = By.cssSelector("div.tab_tab_type_current__2BEPc span");

    @Step("Кликаем по вкладке 'Булки'")
    public void clickBunsTab() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Кликаем по вкладке 'Соусы'")
    public void clickSaucesTab() {

        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Кликаем по вкладке 'Начинки'")
    public void clickFillingsTab() {

        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получаем текст активной вкладки")
    public String getActiveTabText() {
        WebElement active = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return active.getText();
    }

    @Step("Ожидаем, что активная вкладка будет с текстом: {expectedText}")
    public void waitForActiveTab(String expectedText) {

        wait.until(ExpectedConditions.textToBe(activeTab, expectedText));
    }
}