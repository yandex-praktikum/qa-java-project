package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void getPriceReturnsCorrectPrice() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        assertEquals(50f, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void getNameReturnsCorrectName() {
        Ingredient ingredient =
                new Ingredient(IngredientType.FILLING, "cutlet", 200f);
        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void getTypeReturnsCorrectType() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "ketchup", 30f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
