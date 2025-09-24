package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для вкладок
    private By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Булки']]");
    private By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Соусы']]");
    private By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Клик по кнопке 'Войти в аккаунт' через JavaScript")
    public void clickLoginAccount() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        wait.until(ExpectedConditions.urlContains("login"));
    }

    @Step("Ожидание появления кнопки 'Войти в аккаунт'")
    public void waitForLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginAccountButton));
    }

    @Step("Проверка, что раздел '{sectionName}' активен")
    public boolean isSectionActive(String sectionName) {
        By tabLocator = getTabLocator(sectionName);  // Получаем локатор для выбранного раздела
        WebElement sectionTab = wait.until(ExpectedConditions.visibilityOfElementLocated(tabLocator));
        String classValue = sectionTab.getAttribute("class");
        return classValue.contains("tab_tab_type_current");
    }

    @Step("Переход к разделу '{sectionName}' через JavaScript")
    public void goToSection(String sectionName) {
        By tabLocator = getTabLocator(sectionName);  // Получаем локатор для выбранного раздела
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", section);
        wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }

    // Получаем локатор для вкладки по названию раздела
    private By getTabLocator(String sectionName) {
        switch (sectionName) {
            case "Булки":
                return bunsTab;
            case "Соусы":
                return saucesTab;
            case "Начинки":
                return fillingsTab;
            default:
                throw new IllegalArgumentException("Unknown section: " + sectionName);
        }
    }

    // Методы для перехода к конкретным разделам
    public void goToBuns() { goToSection("Булки"); }
    public void goToSauces() { goToSection("Соусы"); }
    public void goToFillings() { goToSection("Начинки"); }
}
