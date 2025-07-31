import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    void constructorShouldSetNameAndPriceCorrectly() {
        Bun bun = new Bun("Sesame", 1.5f);
        assertEquals("Sesame", bun.getName());
        assertEquals(1.5f, bun.getPrice(), 0.0001f);
    }

    @Test
    void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("Brioche", 2.0f);
        assertEquals("Brioche", bun.getName());
    }

    @Test
    void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("Wholegrain", 2.5f);
        assertEquals(2.5f, bun.getPrice(), 0.0001f);
    }
}
