package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeHasSauceValue() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
    }

    @Test
    public void ingredientTypeHasFillingValue() {
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}
