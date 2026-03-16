package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовый класс для проверки модели Ingredient (Ингредиент).
 * Основная цель: проверить, что конструктор корректно инициализирует поля,
 * а методы-геттеры возвращают правильные значения.
 */
@DisplayName("Тестирование модели ингредиента (Ingredient)")
public class IngredientTest {

    /**
     * Параметризованный тест проверяет создание ингредиента с разными типами и ценами.
     * Используем assertAll, чтобы проверить сразу все поля объекта.
     * @param type  Тип ингредиента (SAUCE (соус) или FILLING(начинка))
     * @param name  Название ингредиента
     * @param price Цена ингредиента
     */
    @ParameterizedTest(name = "Кейс: {0} | Название: {1} | Цена: {2}")
    @MethodSource("provideIngredientData")
    @DisplayName("Проверка конструктора и геттеров ингредиента")
    public void ingredientFullTest(IngredientType type, String name, float price) {
        // Arrange: Создаем экземпляр ингредиента с тестовыми параметрами
        Ingredient ingredient = new Ingredient(type, name, price);

        // Act & Assert: Проверяем состояние объекта через геттеры
        assertAll("Проверка полей объекта Ingredient",
                // Проверяем, что тип (SAUCE/FILLING) соответствует заданному
                () -> assertEquals(type, ingredient.getType(), "Тип ингредиента должен соответствовать входному"),

                // Проверяем название
                () -> assertEquals(name, ingredient.getName(), "Имя ингредиента должно соответствовать входному"),

                // Проверяем цену с использованием дельты для float
                () -> assertEquals(price, ingredient.getPrice(), 0.00001f, "Цена должна соответствовать входной")
        );
    }

    /**
     * Метод-источник данных для теста.
     * Позволяет проверить разные типы из перечисления IngredientType за один запуск.
     */
    private static Stream<Arguments> provideIngredientData() {
        return Stream.of(
                // Тест для соуса (позитивный)
                Arguments.of(IngredientType.SAUCE, "hot sauce (горячий соус)", 100.5f),

                // Тест для начинки (позитивный)
                Arguments.of(IngredientType.FILLING, "cutlet (котлетка)", 200f),

                /**
                 * На настоящее время валидация POJO Ingredient отсутствует,
                 * проверим, что он не упадёт, если мы введём невалидные данные.
                 */

                // Граничные значения: пустое название и нулевая цена
                Arguments.of(IngredientType.FILLING, "", 0f),

                // Проверка с null в названии и отрицательной ценой (защитное тестирование)
                Arguments.of(IngredientType.SAUCE, null, -10f),

                // Проверка очень длинного названия
                Arguments.of(IngredientType.FILLING, "Длинное-предлинное название, и пока его читаешь...", 999.99f)
        );
    }
}
