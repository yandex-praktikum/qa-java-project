package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private final Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
        this.bun = new Bun(name, price);
    }

    @Parameterized.Parameters(name = "Bun: {0}, price: {1}")
    public static Object[][] bunData() {
        return new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.0f},
                {"red bun", 300.0f}
        };
    }

    @Test
    public void testGetName() {
        assertEquals("Название булочки не совпадает", name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("Цена булочки не совпадает", price, bun.getPrice(), 0.001f);
    }
}
