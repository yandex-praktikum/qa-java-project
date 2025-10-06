import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    Bun bun = new Bun("Булочка", 100.0f);

    @Test
    void testGetName() {
        assertEquals("Булочка", bun.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(100.0f, bun.getPrice());
    }
}
