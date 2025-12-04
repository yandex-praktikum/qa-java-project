package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void sauceEnumValueExists() {
        assertNotNull(IngredientType.SAUCE);
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void fillingEnumValueExists() {
        assertNotNull(IngredientType.FILLING);
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
