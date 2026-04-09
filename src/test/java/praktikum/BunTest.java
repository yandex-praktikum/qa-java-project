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
    public static Object[][] getBunData() {
        return new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f}
        };
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice(), 0);
    }
}