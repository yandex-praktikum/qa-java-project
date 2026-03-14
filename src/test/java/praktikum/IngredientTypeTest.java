package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValues() {
        IngredientType[] types = IngredientType.values();

        assertEquals("Should have 2 ingredient types", 2, types.length);
        assertEquals("First type should be SAUCE", IngredientType.SAUCE, types[0]);
        assertEquals("Second type should be FILLING", IngredientType.FILLING, types[1]);
    }

    @Test
    public void testIngredientTypeValueOf() {
        assertEquals("SAUCE should be convertible from string",
                IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals("FILLING should be convertible from string",
                IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testIngredientTypeNames() {
        assertEquals("SAUCE enum name should be 'SAUCE'", "SAUCE", IngredientType.SAUCE.name());
        assertEquals("FILLING enum name should be 'FILLING'", "FILLING", IngredientType.FILLING.name());
    }
}