package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void ingredientStoresTypeCorrectly() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili", 50f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void ingredientStoresNameCorrectly() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 200f);
        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void ingredientStoresPriceCorrectly() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 30f);
        assertEquals(30f, ingredient.getPrice(), 0.001);
    }
}