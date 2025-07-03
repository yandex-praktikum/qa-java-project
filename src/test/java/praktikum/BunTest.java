package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"white bun", 100.0f},
                {"black bun", 200.0f},
                {"red bun", 150.0f},
                {"", 0.0f},
                {null, -1.0f}
        });
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunPrice, bun.getPrice(), 0.0f);
    }
}
