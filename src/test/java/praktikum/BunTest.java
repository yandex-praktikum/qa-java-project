package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun("black bun", 1.5f);
        assertEquals("black bun", bun.getName());
        assertEquals(1.5f, bun.getPrice(), 0);
    }

    @Test
    public void testBunWithZeroPrice() {
        Bun bun = new Bun("plain bun", 0f);
        assertEquals(0f, bun.getPrice(), 0);
    }

    @Test
    public void testBunWithNegativePrice() {
        Bun bun = new Bun("defective bun", -1f);
        assertEquals(-1f, bun.getPrice(), 0);
    }

    @Test
    public void testBunWithEmptyName() {
        Bun bun = new Bun("", 2.5f);
        assertEquals("", bun.getName());
    }
}