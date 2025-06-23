package UnitsTests;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testIngredientConstructorAndGetters() {
        IngredientType type = IngredientType.SAUCE;
        String name = "Ketchup";
        float price = 10.5f;

        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testIngredientFieldsAreCorrectlyAssigned() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Lettuce", 15.0f);

        assertEquals(IngredientType.FILLING, ingredient.type);
        assertEquals("Lettuce", ingredient.name);
        assertEquals(15.0f, ingredient.price, 0.001);
    }
}
//