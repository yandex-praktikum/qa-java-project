package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    static Stream<org.junit.jupiter.params.provider.Arguments> dataTests() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(IngredientType.FILLING, generateIngredientName(), generatePrice()),
                org.junit.jupiter.params.provider.Arguments.of(IngredientType.SAUCE, generateIngredientName(), generatePrice())
        );
    }

    private static String generateIngredientName() {
        return "Ингредиент_" + (int)(Math.random() * 1000);
    }

    private static float generatePrice() {
        return (float)(Math.random() * 100);
    }

    @ParameterizedTest
    @MethodSource("dataTests")
    @DisplayName("Проверка метода getPrice")
    void testGetPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.002);
    }

    @ParameterizedTest
    @MethodSource("dataTests")
    @DisplayName("Проверка метода getName")
    void testGetName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @MethodSource("dataTests")
    @DisplayName("Проверка метода getType")
    void testGetType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }
}