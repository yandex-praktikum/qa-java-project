package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValues() {
        IngredientType[] types = IngredientType.values();

        assertEquals("Should have exactly 2 ingredient types", 2, types.length);
        assertEquals("First type should be SAUCE", IngredientType.SAUCE, types[0]);
        assertEquals("Second type should be FILLING", IngredientType.FILLING, types[1]);
    }

    @Test
    public void testIngredientTypeValueOf() {
        IngredientType sauce = IngredientType.valueOf("SAUCE");
        IngredientType filling = IngredientType.valueOf("FILLING");

        assertEquals("SAUCE value should match", IngredientType.SAUCE, sauce);
        assertEquals("FILLING value should match", IngredientType.FILLING, filling);
    }

    @Test
    public void testIngredientTypeToString() {
        assertEquals("SAUCE toString should be 'SAUCE'", "SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING toString should be 'FILLING'", "FILLING", IngredientType.FILLING.toString());
    }
}