import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IngredientTest {

    @ParameterizedTest
    @MethodSource("ingredientData")
    void constructorShouldCreateIngredient(IngredientType type,
                                           String name,
                                           float price) {

        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> ingredientData() {
        return Stream.of(
                arguments(IngredientType.SAUCE, "Ketchup", 50f),
                arguments(IngredientType.FILLING, "Cutlet", 100f)
        );
    }
}