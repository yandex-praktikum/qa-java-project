package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testSauceEnum() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
    }

    @Test
    public void testFillingEnum() {
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test
    public void testEnumValuesCount() {
        assertEquals(2, IngredientType.values().length);
    }
}
