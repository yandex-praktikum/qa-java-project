package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.FILLING, "cheese", 50f},
                {IngredientType.SAUCE, "sour-sweet sauce", 80}
        };
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckIngredientName(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName(),"Неверное название");
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckIngredientPrice(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.01f,"Неверная цена");
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckIngredientType(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType(),"Неверный тип ингридиента");
    }
}

