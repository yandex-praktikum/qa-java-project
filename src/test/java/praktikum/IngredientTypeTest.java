package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        assertEquals(2, IngredientType.values().length);
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
