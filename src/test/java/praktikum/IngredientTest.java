package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IngredientTest {

    private Ingredient ingredient;

    IngredientType type = IngredientType.FILLING;
    String expectedName = "Сыр";
    float expectedPrice = 50.0f;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient(type, expectedName, expectedPrice);
    }

    @Test
    @DisplayName("Конструктор корректно устанавливает значения полей")
    void testConstructor_SetFieldsCorrectly() {
        // Проверяем, что поля установлены правильно
        assertEquals(expectedName, ingredient.name,
                String.format("Значение в bun.name, несоответствует переданному в конструктор.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedName, ingredient.name));
        assertEquals(expectedPrice, ingredient.price, 0.001,
                String.format("Значение в bun.price, несоответствует переданному в конструктор.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedPrice, ingredient.price));
    }

    @Test
    @DisplayName("Метод getName возвращает корректное имя")
    void testGetName_ReturnCurrentValue() {
        assertEquals(expectedName, ingredient.getName(),
                String.format("Метод getName возвращает некорректное значение.\n " +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedName, ingredient.getName()));
    }

    @Test
    @DisplayName("Метод getPrice возвращает корректную цену")
    void testGetPrice_ReturnCorrectPrice() {
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001,
                String.format("Метод getPrice возвращает некорректное значение.\n" +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", expectedPrice, ingredient.getPrice()));
    }

    @Test
    @DisplayName("Метод getTyp возвращает корректный тип")
    void testGetType_ReturnCorrectType() {
        assertEquals(type, ingredient.getType(),
                String.format("Метод getType возвращает некорректное значение.\n" +
                        "Ожидаемое значение: %s.\n" +
                        "Полученное знаение: %s.", type, ingredient.getType()));
    }
}