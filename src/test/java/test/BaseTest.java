package test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import helpers.DriverFactory;


public abstract class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}