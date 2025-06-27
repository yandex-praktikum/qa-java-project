package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @Test
    public void testGetName() {
        Bun bun = new Bun("Test Bun", 150.5f);
        assertEquals("Test Bun", bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun("Test Bun", 150.5f);
        assertEquals(150.5f, bun.getPrice());
    }
}
