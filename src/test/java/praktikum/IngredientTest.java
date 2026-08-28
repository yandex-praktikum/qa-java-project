package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void getTypeShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());   // ← getType()!
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals("hot sauce", ingredient.getName());   // ← без "expected: "
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(100f, ingredient.getPrice(), 0.001);
    }
}
