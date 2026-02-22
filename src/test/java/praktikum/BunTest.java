package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void testConstructorAndGetters() {
        String expectedName = "black bun";
        float expectedPrice = 100f;

        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals("Имя булки должно сохраняться", expectedName, bun.getName());
        assertEquals("Цена булки должна сохраняться", expectedPrice, bun.getPrice(), 0.001);
    }

    @Test
    public void testPublicFieldsAreAccessible() {
        Bun bun = new Bun("test", 50f);

        // Проверяем, что можно напрямую обращаться к публичным полям
        assertEquals("test", bun.name);
        assertEquals(50f, bun.price, 0.001);

        // Меняем значения через поля
        bun.name = "new name";
        bun.price = 75f;

        assertEquals("new name", bun.getName());
        assertEquals(75f, bun.getPrice(), 0.001);
    }
}