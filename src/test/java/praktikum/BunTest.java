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

    @Parameterized.Parameters
    public static Object[][] bunData() {
        return new Object[][] {
                {"Bun_name", 100.00f},
                {"Название_булочки", 0.20f},
                {"", 0.00f}
        };
    }

    @Test
    public void shouldReturnCorrectName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.0);
    }
}