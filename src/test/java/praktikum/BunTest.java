package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"white bun", 100.0f},
                {"black bun", 150.5f},
                {"red bun", 200.0f}
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void testGetName() {
        assertEquals(name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, bun.getPrice(), 0.001);
    }
}