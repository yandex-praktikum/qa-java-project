package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        IngredientType[] expected = {IngredientType.SAUCE, IngredientType.FILLING};
        assertArrayEquals(expected, IngredientType.values());
    }

    @Test
    public void testValueOfSauce() {
        IngredientType type = IngredientType.valueOf("SAUCE");
        assertEquals(IngredientType.SAUCE, type);
    }

    @Test
    public void testValueOfFilling() {
        IngredientType type = IngredientType.valueOf("FILLING");
        assertEquals(IngredientType.FILLING, type);
    }
}
