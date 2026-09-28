package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    void constructor_shouldSetName() {
        String expectedName = "black bun";

        Bun bun = new Bun(expectedName, 100f);

        assertEquals(expectedName, bun.getName());
    }

    @Test
    void constructor_shouldSetPrice() {
        float expectedPrice = 100f;

        Bun bun = new Bun("black bun", expectedPrice);

        assertEquals(expectedPrice, bun.getPrice());
    }
}