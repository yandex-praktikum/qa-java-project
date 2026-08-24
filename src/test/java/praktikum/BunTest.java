package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    public void getNameReturnsCorrectName() {
        Bun bun = new Bun("black bun", 100.0f);

        String actualName = bun.getName();

        assertEquals("black bun", actualName);
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Bun bun = new Bun("black bun", 100.0f);

        float actualPrice = bun.getPrice();

        assertEquals(100.0f, actualPrice, 0.001f);
    }
}