package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTests {
    private String bunName;
    private float bunPrice;

    public BunTests(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }
    @Parameterized.Parameters
    public static Object[][] bunData() {
        return new Object[][]{
                {"random bun", 10f},
                {"simple bun", 5f},
                {"булочка дня", 7f},
                {"vip bun", 1077f}
        };
    }
    @Test
    public void getNameTest() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunName, bun.getName());
    }
    @Test
    public void getPriceTest() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunPrice, bun.getPrice(), 0);
    }
}
