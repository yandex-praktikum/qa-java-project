package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    void ingredientParametersCorrect(IngredientType ingredientType,String name, float price){
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertAll(
                () -> assertEquals(ingredientType, ingredient.getType()),
                () -> assertEquals(name, ingredient.getName()),
                () -> assertEquals(price, ingredient.getPrice(), 0.01f)
        );
    }

    private static Stream<Arguments> dataProvider() {
        Database data = new Database();
        return Stream.of(
                Arguments.of(data.availableIngredients().get(0).getType(),data.availableIngredients().get(0).getName(), data.availableIngredients().get(0).getPrice()),
                Arguments.of(data.availableIngredients().get(3).getType(),data.availableIngredients().get(3).getName(), data.availableIngredients().get(3).getPrice())
        );
    }


}
