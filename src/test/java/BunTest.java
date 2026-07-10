import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    private Bun bun;

    @BeforeEach
    void setUp() {
        bun = new Bun("Булочка", 100.2f);
    }

    @Test
    void getNameShouldReturnName() {
        assertEquals("Булочка", bun.getName());
    }

    @Test
    void getPriceShouldReturnPrice() {
        assertEquals(100.2f, bun.getPrice());
    }
}
