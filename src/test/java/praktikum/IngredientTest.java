package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @ParameterizedTest
    @MethodSource("ingredients")
    @DisplayName("Создание с различными входными данными")
    public void testCreateIngredientWithDifferentParameters(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType(), "Тип должен совпадать");
        assertEquals(name, ingredient.getName(), "Имя должно совпадать");
        assertEquals(price, ingredient.getPrice(), "Цена должна совпадать");
    }

    private static Stream<Arguments> ingredients() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "Соус Spicy-X", 90.0f),
                Arguments.of(IngredientType.SAUCE, "Соус фирменный Space Sauce", 80.0f),
                Arguments.of(IngredientType.FILLING, "Соус традиционный галактический", 15.0f),
                Arguments.of(IngredientType.FILLING, "Соус с шипами Антарианского плоскоходца", 45.0f),
                Arguments.of(IngredientType.SAUCE, "", 0.0f)
        );
    }
}