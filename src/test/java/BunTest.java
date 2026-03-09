package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    private Bun bun;

    @BeforeEach
    void setUp() {
        bun = new Bun("black bun", 100);
    }

    @Test
    @DisplayName("Метод getName возвращает имя булочки")
    void getNameShouldReturnBunName() {
        assertEquals("black bun", bun.getName());
    }

    @Test
    @DisplayName("Метод getPrice возвращает цену булочки")
    void getPriceShouldReturnBunPrice() {
        assertEquals(100, (int) bun.getPrice());
    }

    @Test
    @DisplayName("Конструктор корректно сохраняет параметры")
    void constructorShouldSaveFields() {
        Bun testBun = new Bun("white bun", 200);

        assertEquals("white bun", testBun.getName());
        assertEquals(200, (int) testBun.getPrice());
    }
}