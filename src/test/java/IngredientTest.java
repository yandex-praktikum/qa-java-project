
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IngredientTest {

    private static Stream<Arguments> listIngredients() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "ketchup", 120.00F),
                Arguments.of(IngredientType.FILLING, "cheese", 55.55F)
        );
    }

    @ParameterizedTest
    @MethodSource("listIngredients")
    void constructorIngredientTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.type, "Тип ингредиента не совпадает с переданным");
        assertEquals(name, ingredient.name, "Название ингредиента не совпадает с переданным");
        assertEquals(price, ingredient.price, "Цена ингредиента не совпадает с переданным");

    }
}
