package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IngredientTest {
    private static Stream<Arguments> ingredientTestData() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "Соус традиционный галактический", 100f),
                Arguments.of(IngredientType.FILLING, "Сыр с астероидной плесенью", 200.5f),
                Arguments.of(IngredientType.SAUCE, "", 0f)
        );
    }

    @ParameterizedTest(name = "Тест ингредиентов: тип={0}, имя={1}, цена={2}")
    @MethodSource("ingredientTestData")
    public void testIngredientGetters(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assertions.assertEquals(type, ingredient.getType());
        Assertions.assertEquals(name, ingredient.getName());
        Assertions.assertEquals(price, ingredient.getPrice(), 0.0f);
    }
}
