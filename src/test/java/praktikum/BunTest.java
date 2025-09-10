package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    Bun bun; // Объект тестируемого класса Bun
    private static final String BUN_NAME = "white"; // Константа имени булочки
    private static final float BUN_PRICE = 100; // Константа цены булочки

    @BeforeEach
    void setUp() {
        bun = new Bun(BUN_NAME, BUN_PRICE); // Инициализация объекта перед каждым тестом
    }

    @Test
    @DisplayName("Получение имени бургера")
    void getName() {
        assertEquals(BUN_NAME, bun.getName()); // Проверка корректности имени булочки
    }

    @Test
    @DisplayName("Получение цены бургера")
    void getPrice() {
        assertEquals(BUN_PRICE, bun.getPrice()); // Проверка корректности цены булочки
    }
}
