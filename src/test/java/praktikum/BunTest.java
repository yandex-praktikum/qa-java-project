package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String expectedName;
    private final float expectedPrice;
    private Bun bun;

    public BunTest(String expectedName, float expectedPrice) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void setUp() {
        bun = new Bun(expectedName, expectedPrice);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100f},
                {"white bun", 200.5f},
                {"red bun", 300.99f},
                {"", 0f},
                {"special bun", -50f}
        };
    }

    @Test
    public void testGetName() {
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, bun.getPrice(), 0.001);
    }
}