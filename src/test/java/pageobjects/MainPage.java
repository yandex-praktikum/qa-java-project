package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.education-services.ru/";

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка "Войти"
    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    //Кнопка "Личный кабинет"
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");

    public void clickLoginAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginAccountButton))
                .click();

    }

    public void clickProfileButton() {

        driver.findElement(profileButton).click();
    }
}