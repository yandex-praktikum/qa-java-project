package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    private Bun bun;

    private final String expectedName = "Космическая булка";
    private final float expectedPrice = 1250.10f;

    @BeforeEach
    void setUp() {
        bun = new Bun(expectedName, expectedPrice);
    }

    @Test
    @DisplayName("Конструктор корректно устанавливает значения полей")
    void testConstructor_SetFieldsCorrectly() {
        // Проверяем, что поля установлены правильно
        assertEquals(expectedName, bun.name,
                String.format("Значение в bun.name, несоответствует переданному в конструктор.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedName, bun.name));
        assertEquals(expectedPrice, bun.price, 0.001,
                String.format("Значение в bun.price, несоответствует переданному в конструктор.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedPrice, bun.price));
    }

    @Test
    @DisplayName("Метод getName возвращает корректное имя")
    void testGetName_ReturnCurrentValue() {
        assertEquals(expectedName, bun.getName(),
                String.format("Метод getName возвращает некорректное значение.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedName, bun.getName()));
    }

    @Test
    @DisplayName("Метод getPrice возвращает корректную цену")
    void testGetPrice_ReturnCorrectPrice() {
        assertEquals(expectedPrice, bun.getPrice(), 0.001,
                String.format("Метод getPrice возвращает некорректное значение.\n" +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedPrice, bun.getPrice()));
    }
}