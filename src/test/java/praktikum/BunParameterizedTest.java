package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static helper.Constants.DELTA_FLOAT_BOUNED;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private final String bunName;
    private final float bunPrice;

    public BunParameterizedTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters(name = "bunTest {index}: for name: {0}, price {1}")
    public static Object[][] getBunData() {
        return new Object[][] {
                {"bun", 100},
                {" bun", 100},
                {"bun ", 100},
                {"b", 100},
                {"", 100},
                {" ", 100},
                {null, 100},
                {"123", 100},
                {"!&?.,", 100},
                {"black bun", 100},
                {"black bun", 1000000},
                {"black bun", 100.1f},
                {"black bun", 0},
                {"black bun", -0.1f},
                {"black bun", -100},
        };
    }

    @Test
    public void getNameReturnBunNameTest() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bunName, bun.getName());
    }

    @Test
    public void getPriceReturnBunPriceTest() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bunPrice,bun.getPrice(), DELTA_FLOAT_BOUNED);
    }
}
