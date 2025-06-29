package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IngredientTest {


    @ParameterizedTest
    @MethodSource("params")
    public void getPriceShouldReturnPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001f, "Цена не совпадает!");
    }

    @ParameterizedTest
    @MethodSource("params")
    public void getNameShouldReturnName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName(), "Имя не совпадает!");
    }

    @ParameterizedTest
    @MethodSource("params")
    public void getTypeShouldReturnType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType(), "Тип не совпадает!");
    }


    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(IngredientType.FILLING, "cutlet", 10.00f),
                Arguments.of(IngredientType.SAUCE, "hot sauce", 5.00f),
                Arguments.of(IngredientType.FILLING, "dinosaur", 50.00f),
                Arguments.of(IngredientType.SAUCE, "chili sauce", 5.00f)
        );
    }
}
