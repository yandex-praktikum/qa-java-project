package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTests {

    IngredientType ingredientType = IngredientType.SAUCE;
    String name = "hot sauce";
    float price = 100;

    @Test
    void testGetNameReturnsIngredientType() {

        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        IngredientType actualIngredientType = ingredient.getType();

        assertEquals(ingredientType.toString(), actualIngredientType.toString());
    }

    @Test
    void testGetNameReturnsIngredientName() {

        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        String actualIngredientName = ingredient.getName();

        assertEquals(name, actualIngredientName);
    }

    @Test
    void testGetPriceReturnsIngredientPrice() {

        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        float actualIngredientPrice = ingredient.getPrice();

        assertEquals(price, actualIngredientPrice);
    }
}
