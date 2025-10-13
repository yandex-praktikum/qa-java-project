package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @ParameterizedTest
    @MethodSource("ingredientParameters")
    public void getPriceTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice());
    }

    @ParameterizedTest
    @MethodSource("ingredientParameters")
    public void getNameTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @MethodSource("ingredientParameters")
    public void getTypeTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    private static Stream<Arguments> ingredientParameters() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "hot sauce", 100.0f),
                Arguments.of(IngredientType.FILLING, "cutlet", 200.0f),
                Arguments.of(IngredientType.SAUCE, "sour cream", 0.0f),
                Arguments.of(IngredientType.FILLING, "dinosaur", -150.5f)
        );
    }
}