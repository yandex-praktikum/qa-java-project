package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Name: {0}, Price: {1}")
    public static Object[][] getData() {
        return new Object[][] {
                {"Black Bun", 100.0f},
                {"White Bun", 200.50f},
                {"", 0.0f},
                {null, -100f}
        };
    }

    @Test
    public void getNameReturnCorrectName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceReturnCorrectPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.01);
    }
}
