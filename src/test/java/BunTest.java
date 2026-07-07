import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    @Test
    void constructorShouldCreateBun() {
        Bun bun = new Bun("Black Bun", 10f);

        assertEquals("Black Bun", bun.getName());
        assertEquals(10f, bun.getPrice());
    }
}