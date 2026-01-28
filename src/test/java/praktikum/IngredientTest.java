package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void constructorShouldSetTypeNameAndPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "spicy sauce", 0.75f);

        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("spicy sauce", ingredient.getName());
        assertEquals(0.75f, ingredient.getPrice(), 0.0001f);
    }

    @Test
    public void getNameShouldReturnProvidedName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 1.20f);

        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnProvidedPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "salad", 0.50f);

        assertEquals(0.50f, ingredient.getPrice(), 0.0001f);
    }

    @Test
    public void getTypeShouldReturnProvidedType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 0.30f);

        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
