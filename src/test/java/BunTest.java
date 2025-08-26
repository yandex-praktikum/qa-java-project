import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    void testBunGetters() {
        Bun bun = new Bun("Black Bun", 2.5f);
        assertEquals("Black Bun", bun.getName());
        assertEquals(2.5f, bun.getPrice());
    }

    @Test
    void testBunDifferentValues() {
        Bun bun = new Bun("White Bun", 1.0f);
        assertEquals("White Bun", bun.getName());
        assertEquals(1.0f, bun.getPrice());
    }
}
