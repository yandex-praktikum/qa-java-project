package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @Test
    public void testBunConstructorAndGetters() {
        String expectedName = "testBun";
        float expectedPrice = 1.5f;

        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.getName(),
                "Имя булочки должно совпадать с заданным в конструкторе");
        assertEquals(expectedPrice, bun.getPrice(),
                "Цена булочки должна совпадать с заданной в конструкторе");
    }

    @Test
    public void testBunWithZeroPrice() {
        String expectedName = "zeroBun";
        float expectedPrice = 0.0f;

        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.getName());
        assertEquals(expectedPrice, bun.getPrice(), 0);
    }

    @Test
    public void testBunWithNegativePrice() {
        String expectedName = "negativeBun";
        float expectedPrice = -5.5f;

        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.getName());
        assertEquals(expectedPrice, bun.getPrice());
    }

    @Test
    public void testBunWithNullName() {
        Bun bun = new Bun(null, 100);

        assertNull(bun.getName(), "Имя может быть null");
        assertEquals(100, bun.getPrice());
    }
}