package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testGetType() {
        Ingredient ing = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ing.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ing = new Ingredient(IngredientType.FILLING, "cutlet", 200f);
        assertEquals("cutlet", ing.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ing = new Ingredient(IngredientType.FILLING, "dinosaur", 300f);
        assertEquals(300f, ing.getPrice(), 0.001f);
    }
}