import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeTest {

    @Test
    void testEnumValuesExist() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
        assertTrue(contains(values, IngredientType.SAUCE));
        assertTrue(contains(values, IngredientType.FILLING));
    }

    private boolean contains(IngredientType[] values, IngredientType value) {
        for (IngredientType v : values) {
            if (v == value) return true;
        }
        return false;
    }

    @Test
    void testValueOf() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    void testValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> IngredientType.valueOf("INVALID"));
    }
}
