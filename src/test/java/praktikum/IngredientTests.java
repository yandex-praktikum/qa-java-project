package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTests {

    @Test
    public void shouldReturnName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "бекон", 100f);
        assertEquals("бекон", ingredient.getName());
    }

    @Test
    public void shouldReturnPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "сырный", 100f);
        assertEquals(100f, ingredient.getPrice(), 0.0001f);
    }

    @Test
    public void shouldReturnType() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "бекон", 100f);
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}
