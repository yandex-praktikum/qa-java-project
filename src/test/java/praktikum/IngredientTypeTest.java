package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeShouldContainSauce() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void ingredientTypeShouldContainFilling() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}