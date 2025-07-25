
package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void shouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Spicy Sauce", 150);
        assertEquals(150, ingredient.getPrice(), 0);
    }

    @Test
    public void shouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Beef Patty", 200);
        assertEquals("Beef Patty", ingredient.getName());
    }

    @Test
    public void shouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Garlic Sauce", 100);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}