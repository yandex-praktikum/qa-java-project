package praktikum.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By breadsTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(1)");
    private final By sauceTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(2)");
    private final By toppingsTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(3)");
    private final By breadsAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Булки']");
    private final By sauceAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Соусы']");
    private final By toppingsAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Начинки']");
    private final By lastLocate = By.tagName("script");
    private final By personalAccountButtonPage = By.cssSelector("a.AppHeader_header__link__3D_hX[href='/account']");
    private final By mainPageLocator = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");
    private final By loginInMainPage = By.cssSelector("section.BurgerConstructor_basket__29Cd7.mt-25 > div > button");

    @Step("Открыть главную страницу")
    public void openMainPage() {
        driver.get(Config.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }
    @Step("Нажать кнопку входа в конструкторе")
    public void loginInMainPageClick() {
        driver.findElement(loginInMainPage).click();
    }


    @Step("Проверить, что главная страница загружена")
    public boolean mainPageLoaded() {
        return driver.findElement(mainPageLocator).isDisplayed();
    }

    @Step("Нажать кнопку Личный кабинет")
    public void personalAccountButtonPageClick() {
        driver.findElement(personalAccountButtonPage).click();
    }
    @Step("Кликнуть вкладку 'Булки'")
    public void clickBreadsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.elementToBeClickable(breadsTab))
                .click();

    }
    @Step("Найти якорь 'Булки' и дождаться видимости")
    public WebElement  findBreadsAnchor() {
        WebElement breadsAnchorLocator = driver.findElement(breadsAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(breadsAnchorLocator));
        return  breadsAnchorLocator;
    }
    @Step("Проверить, что якорь 'Булки' в видимой области (между 100 и 250 px от верха окна)")
    public boolean isBreadsAnchorInViewport() {
        WebElement element = findBreadsAnchor();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Number top = (Number) js.executeScript(
                "return arguments[0].getBoundingClientRect().top;", element);
        int topPx = (int) Math.round(top.doubleValue());

        int min = 100; // не выше (>= min)
        int max = 250; // не ниже (<= max)

        return topPx >= min && topPx <= max;
    }
    @Step("Дождаться, пока якорь 'Булки' не окажется в требуемой области")
    public void waitForBreadsAnchorInViewport() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> isBreadsAnchorInViewport());
    }

    @Step("Кликнуть вкладку 'Соусы'")
    public void clickSauceTab() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.elementToBeClickable(sauceTab))
                .click();
    }
    @Step("Найти якорь 'Соусы' и дождаться видимости")
    public WebElement findSauceAnchor() {
        WebElement sauceAnchorLocator = driver.findElement(sauceAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(sauceAnchorLocator));
        return sauceAnchorLocator;
    }
    @Step("Проверить, что якорь 'Соусы' в видимой области (центр элемента в допустимом диапазоне)")
    public boolean isSauceAnchorInViewport() {
        WebElement element = findSauceAnchor();
        return element.getLocation().getY() < 250;
    }
    @Step("Дождаться, пока якорь 'Соусы' не окажется в требуемой области")
    public void waitForSauceAnchorInViewport() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> isSauceAnchorInViewport());
    }
    @Step("Кликнуть вкладку 'Начинки'")
    public void clickToppingsTab() {
        driver.findElement(toppingsTab).click();

    }
    @Step("Найти якорь 'Начинки' и дождаться видимости")
    public WebElement findToppingsAnchor() {
        WebElement toppingsAnchorLocator = driver.findElement(toppingsAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(toppingsAnchorLocator));
        return toppingsAnchorLocator;
    }
    @Step("Проверить, что якорь 'Начинки' в видимой области (центр элемента в допустимом диапазоне)")
    public boolean isToppingsAnchorInViewport() {
        WebElement element = findToppingsAnchor();
        return element.getLocation().getY() < 250;
    }
    @Step("Дождаться, пока якорь 'Начинки' не окажется в требуемой области")
    public void waitForToppingsAnchorInViewport() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> isToppingsAnchorInViewport());
    }


}

