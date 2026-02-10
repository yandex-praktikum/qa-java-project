package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование класса Ingredient")
public class IngredientTests {

    @DisplayName("Создание ингредиента и проверка всех полей")
    @ParameterizedTest(name = "Тип={0}, Название={1}, Цена={2}")
    @MethodSource("ingredientProvider")
    void testIngredientCreateAndCheck(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertAll(
                () -> assertEquals(type, ingredient.getType(), "Тип должен совпадать"),
                () -> assertEquals(name, ingredient.getName(), "Название должно совпадать"),
                () -> assertEquals(price, ingredient.getPrice(), "Цена должна совпадать")
        );
    }

    private static Stream<Arguments> ingredientProvider() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "Соус", 100.0f),
                Arguments.of(IngredientType.FILLING, "Начинка", 200.0f),
                Arguments.of(null, "Без типа", 0.0f),
                Arguments.of(IngredientType.SAUCE, "", 999.99f)
        );
    }

    @DisplayName("Работа с моком типа ингредиента")
    @Test
    void testWithMockType(@Mock IngredientType mockedType) {
        String expectedName = "Тестовый ингредиент";
        float expectedPrice = 150.0f;

        Ingredient ingredient = new Ingredient(mockedType, expectedName, expectedPrice);

        assertSame(mockedType, ingredient.getType(), "Должен использоваться мок типа");
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice());
    }
}
