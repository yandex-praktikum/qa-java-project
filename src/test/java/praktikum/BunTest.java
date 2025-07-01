package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {
    Bun bun;

    @BeforeEach
    void setUp() {
        bun = new Bun("black bun", 100.0f);
    }

    @Test
    void getNameTest() {
        assertEquals("black bun", bun.getName(), "Название должно быть корректным");
    }

    @Test
    void getPriceTest() {
        assertEquals(100.0f, bun.getPrice(), "Цена должна быть корректной");
    }
}