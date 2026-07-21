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

    @Parameterized.Parameters(name = "Булочка: {0}, Цена: {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.5f},
                {"red bun", 0.0f},
                {"", 50.0f},
                {null, -100.0f}
        };
    }

    @Test
    public void getNameReturnsCorrectName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.01f);
    }
}
