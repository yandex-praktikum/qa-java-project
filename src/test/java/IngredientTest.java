import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private static final Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Соус", 50.0f);

    @Test
    void testGetPrice() {
        assertEquals(50.0f, ingredient.getPrice());
    }

    @Test
    void testGetName() {
        assertEquals("Соус", ingredient.getName());
    }

    @Test
    void testGetType() {
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

}
