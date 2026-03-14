package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {
    private Bun bun;

    @BeforeEach
    void setUp() {
        bun = new Bun("black bun", 100.0f);  // Инициализация перед каждым тестом
    }

    @Test
    @DisplayName("Проверка создании булочки")
    void BunSettingTest() {
    Bun bun = new Bun("black bun", 100.0f);
        assertEquals("black bun", bun.getName(), "Название булочки не совпадает");

    }

    @Test
    @DisplayName("Проверка установки цены")
    void PriceSettingTest(){
        assertEquals(100.0f, bun.getPrice(), "Цена булочки не совпадает");
    }
}