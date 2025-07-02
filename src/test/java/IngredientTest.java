import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

public class IngredientTest {
    
    @ParameterizedTest
    @MethodSource("ingredientData")
    public void getPrice(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type,name, price);
        Assertions.assertEquals(price, ingredient.getPrice());
    }

    @ParameterizedTest
    @MethodSource("ingredientData")
    public void getName(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type,name, price);
        Assertions.assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @MethodSource("ingredientData")
    public void getType (IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assertions.assertEquals(type, ingredient.getType());
    }

    // Метод, предоставляющий данные для тестов
    private static Stream<Arguments> ingredientData() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "С корицей", 10f),
                Arguments.of(IngredientType.FILLING, "С кунжутом", 20f)
        );
    }
}
