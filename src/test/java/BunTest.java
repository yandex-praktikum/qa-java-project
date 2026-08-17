import praktikum.Bun;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private String bunName;
    private float bunPrice;
    private Bun bun;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Before
    public void setUp() {
        bun = new Bun(bunName, bunPrice);
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f}
        };
    }
    @Test
    public void testGetName() {
        assertEquals(bunName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(bunPrice, bun.getPrice(), 0.001f);
    }
    }