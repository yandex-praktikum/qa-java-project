package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    public static Object[][] data() {
        return new Object[][]{
                {SAUCE, "Острый соус", 20},
                {FILLING, "Куриная котлета", 90},
                {SAUCE, "Томатный соус", 20},
                {FILLING, "Говяжая котлета", 100}
        };
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getNameTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName(), "Ожидается, что вернется название ингредиента: " + name);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getPriceTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), "Ожидается, что вернется цена ингредиента: " + price);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getTypeTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType(), "Ожидается, что вернется тип ингредиента: " + type);
    }
}
