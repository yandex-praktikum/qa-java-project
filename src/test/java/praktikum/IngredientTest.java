package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @ParameterizedTest(name = "[{index}] Тип: {0}, Название: {1}, Цена: {2}")
    @MethodSource("testData")
    @DisplayName("Проверка создания ингредиента и его методов")
    void testIngredient(IngredientType type, String name, float price) {
        // Arrange
        Ingredient ingredient = new Ingredient(type, name, price);

        // Act & Assert
        assertAll(
                () -> assertEquals(type, ingredient.getType(),
                        "Тип ингредиента должен соответствовать переданному в конструктор"),
                () -> assertEquals(name, ingredient.getName(),
                        "Название ингредиента должно соответствовать переданному в конструктор"),
                () -> assertEquals(price, ingredient.getPrice(), 0.001f,
                        "Цена ингредиента должна соответствовать переданной в конструктор")
        );
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                // Основные сценарии для соусов
                Arguments.of(IngredientType.SAUCE, "Соус классический", 100.0f),
                Arguments.of(IngredientType.SAUCE, "Сырный соус", 150.0f),

                // Основные сценарии для начинок
                Arguments.of(IngredientType.FILLING, "Говяжий метеорит", 200.0f),
                Arguments.of(IngredientType.FILLING, "Сыр", 80.0f),

                // Граничный случай с нулевой ценой
                Arguments.of(IngredientType.SAUCE, "Бесплатный соус", 0.0f),

                // Граничный случай с дробной ценой
                Arguments.of(IngredientType.FILLING, "Бекон", 150.5f),

                // Граничный случай с пустым названием
                Arguments.of(IngredientType.FILLING, "", 100.0f)
        );
    }
}
