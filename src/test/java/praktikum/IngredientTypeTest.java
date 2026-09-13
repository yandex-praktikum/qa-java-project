package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 300}
        };
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckIngredientTypePositiveResult(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type,ingredient.getType(),"Неверное название");
    }
}
