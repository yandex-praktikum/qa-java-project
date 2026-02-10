package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;
import java.util.stream.Stream;

public class IngredientTest {

    private Ingredient ingredient;

    @ParameterizedTest(name = "Тип ингредиента: {0}. Название ингредиента: {1}. Цена: {2}")
    @MethodSource("dataForTest")
    public void ingredientTypeTest(IngredientType type, String name, float price) {
        ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.type);
        assertEquals(name, ingredient.name);
        assertEquals(price, ingredient.price, 0.00f);
    }

    static Stream<Arguments> dataForTest() {
        return Stream.of(
                Arguments.of(SAUCE, "Соус мятный", 17.99f),
                Arguments.of(FILLING, "Лайм", 99.99f),
                Arguments.of(SAUCE, "Соус галактический", 13.07f),
                Arguments.of(FILLING, "Черная дыра", 123.45f)
        );
    }
}
