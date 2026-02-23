package praktikum;

import org.junit.Test;
import praktikum.IngredientType;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testEnumValuesAreCorrect() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testEnumHasTwoValuesOnly() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void testToStringReturnsCorrectNames() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValueThrowsException() {
        IngredientType.valueOf("UNKNOWN");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyIngredientType() {
        IngredientType.valueOf("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedIngredientTypes() {
        IngredientType.valueOf("UNSUPPORTED_TYPE");
    }
}