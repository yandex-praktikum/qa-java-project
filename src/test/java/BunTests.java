import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import utils.Utils;

public class BunTests {
    private Bun bun;
    private String expectedName;
    private float expectedPrice;

    @Before
    public void createBunWithRandomValues() {
        expectedName = Utils.randomName();
        expectedPrice = Utils.randomPrice();

        bun = new Bun(expectedName, expectedPrice);
    }

    @Test
    public void testGetName() {
        String actualName = bun.getName();
        Assert.assertEquals("Фактическое название булочки отличается от ожидаемого", expectedName, actualName);
    }

    @Test
    public void testGetPrice() {
        float actualPrice = bun.getPrice();
        Assert.assertEquals("Фактическое значение цены отличается от ожидаемого", expectedPrice, actualPrice, 0.0001);

    }
}
