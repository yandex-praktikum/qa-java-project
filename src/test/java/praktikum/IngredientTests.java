package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTests {

    @ParameterizedTest
    @MethodSource("getIngredientReturnValid")
    void getIngredientCorrect(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type,name,price);
        assertEquals(type,ingredient.getType(),"Expected: " + name + ", but actual: " + ingredient.getType());
        assertEquals(name,ingredient.getName(),"Expected: " + name + ", but actual: " + ingredient.getName());
        assertEquals(price,ingredient.getPrice(),"Expected: " + price + ", but actual: " + ingredient.getPrice());
    }

    private static Stream<Arguments> getIngredientReturnValid(){
        return Stream.of(
                Arguments.of(SAUCE,"farsh", 99.9f),
                Arguments.of(SAUCE,"", 0),
                Arguments.of(SAUCE,"Бургер соус!", 0.001f),
                Arguments.of(SAUCE,"Чили", 2),
                Arguments.of(SAUCE,"BBQ булочка 123", -11),
                Arguments.of(FILLING,"farsh", 99.9f),
                Arguments.of(FILLING,"", 0),
                Arguments.of(FILLING,"Бургер соус!", 0.001f),
                Arguments.of(FILLING,"Чили", 2),
                Arguments.of(FILLING,"BBQ булочка 123", -11)
        );
    }
}
