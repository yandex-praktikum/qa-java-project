package data;

import org.junit.jupiter.params.provider.Arguments;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.stream.Stream;

public class ParamSource {
    private static TestData data = new TestData();

    public static Stream<Arguments> ingredientTypes() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE,
                        new Ingredient(IngredientType.SAUCE, data.getName(), data.getPrice())),
                Arguments.of(IngredientType.FILLING,
                        new Ingredient(IngredientType.FILLING, data.getName(), data.getPrice()))
        );
    }
}