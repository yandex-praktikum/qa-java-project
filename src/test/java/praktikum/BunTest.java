package praktikum;

import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters(name = "Bun: {0}, Price: {1}")
    public static Object[][] data() {
        return new Object[][]{
                {"Краторная булка N-2001", 1255f},
                {"Флюоресцентная булка R2-D3", 988f}
        };
    }

    @Test
    public void bunNameIsReturnedCorrectlyTest() {
        assertEquals(name, bun.getName());
    }

    @Test
    public void bunPriceIsReturnedCorrectlyTest() {
        assertEquals(price, bun.getPrice(), 0.01);
    }
}