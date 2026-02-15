package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTests {

    @Test
    public void shouldHaveSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void shouldHaveFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
