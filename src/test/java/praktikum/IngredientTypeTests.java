package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTests {
    @Test
    public void sauceIngredientTypeTest() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
    }
    @Test
    public void fillingIngredientTypeTest() {
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}
