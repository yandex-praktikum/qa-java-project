package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private final String expectedName;
    private final float expectedPrice;

    public BunTest(String name, float price, String expectedName, float expectedPrice) {
        this.name = name;
        this.price = price;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"black bun", 100, "black bun", 100},
                {"white bun", 200, "white bun", 200},
                {"red bun", 300, "red bun", 300},
                {"", 0, "", 0},
                {"special bun", 150.5f, "special bun", 150.5f}
        };
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(expectedPrice, bun.getPrice(), 0.001);
    }
}