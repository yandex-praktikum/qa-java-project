package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientParamTest {

    static Stream<Arguments> ingredientProvider() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "Hot Sauce", 50.0f),
                Arguments.of(IngredientType.FILLING, "Cutlet", 100.0f)
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientProvider")
    public void getNameReturnsCorrectName(IngredientType type, String name, float price) {
        assertEquals(name, new Ingredient(type, name, price).getName());
    }

}
