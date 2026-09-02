import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;

public class IngredientTest {
    @Test
    public void testIngredientConstructorAndGetters() {
        IngredientType expectedType = IngredientType.SAUCE;
        String expectedName = "hot sauce";
        float expectedPrice = 100.0f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.getType());
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice());
    }

    @ParameterizedTest
    @MethodSource("ingredientDataProvider")
    public void testIngredientWithDifferentParameters(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }
    private static Stream<Arguments> ingredientDataProvider() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "hot sauce", 100.0f),
                Arguments.of(IngredientType.SAUCE, "sour cream", 200.0f),
                Arguments.of(IngredientType.SAUCE, "chili sauce", 300.0f),
                Arguments.of(IngredientType.FILLING, "cutlet", 150.5f),
                Arguments.of(IngredientType.FILLING, "dinosaur", 0.0f),
                Arguments.of(IngredientType.FILLING, "sausage", 999.99f),
                Arguments.of(IngredientType.SAUCE, null, 50.0f),
                Arguments.of(IngredientType.FILLING, "", 75.0f)
        );
    }
    @ParameterizedTest
    @MethodSource("ingredientPriceProvider")
    public void testIngredientWithDifferentPrices(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "test", price);

        assertEquals(price, ingredient.getPrice());
    }
    private static Stream<Arguments> ingredientPriceProvider() {
        return Stream.of(
                Arguments.of(-100.0f),
                Arguments.of(0.0f),
                Arguments.of(100.0f),
                Arguments.of(999.99f),
                Arguments.of(-0.5f)
        );
    }
    @Test
    public void testIngredientWithNullName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, null, 100.0f);
        assertNull(ingredient.getName());
        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals(100.0f, ingredient.getPrice());
    }
    @Test
    public void testIngredientTypeEnumValues() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "sauce", 100.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "filling", 100.0f);
        assertEquals(IngredientType.SAUCE, ingredient1.getType());
        assertEquals(IngredientType.FILLING, ingredient2.getType());
        assertNotEquals(ingredient1.getType(), ingredient2.getType());
    }

}
