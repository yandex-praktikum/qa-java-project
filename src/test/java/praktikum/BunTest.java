package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
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
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f},
                {"white bun", 200.0f},
                {"red bun", 300.0f},
                {"", 0.0f},
                {null, -1.0f}
        });
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertEquals("Name should match constructor parameter", name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertEquals("Price should match constructor parameter", price, bun.getPrice(), 0.001);
    }
    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun("test bun", 123.45f);

        assertEquals("test bun", bun.getName());
        assertEquals(123.45f, bun.getPrice(), 0.001);
    }

    @Test
    public void testBunWithSpecialCharacters() {
        Bun bun = new Bun("bun_with-special@chars", 999.99f);

        assertEquals("bun_with-special@chars", bun.getName());
        assertEquals(999.99f, bun.getPrice(), 0.001);
    }
}