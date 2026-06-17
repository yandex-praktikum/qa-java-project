package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BunTest {

    @Test
    @DisplayName("Создание булочки с корректными параметрами")
    public void testBunCreation() {
        Bun bun = new Bun("black bun", 100);

        Assertions.assertEquals("black bun", bun.getName());
        Assertions.assertEquals(100, bun.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Создание булочки с нулевой ценой")
    public void testBunWithZeroPrice() {
        Bun bun = new Bun("free bun", 0);

        Assertions.assertAll(
                () -> Assertions.assertEquals("free bun", bun.getName()),
                () -> Assertions.assertEquals(0, bun.getPrice(), 0.001)
        );
    }

    @Test
    @DisplayName("Создание булочки с дробной ценой")
    public void testBunWithFractionalPrice() {
        Bun bun = new Bun("expensive bun", 99.99f);

        Assertions.assertAll(
                () -> Assertions.assertEquals("expensive bun", bun.getName()),
                () -> Assertions.assertEquals(99.99f, bun.getPrice(), 0.001)
        );
    }
}