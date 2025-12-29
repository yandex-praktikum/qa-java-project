package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    void testEnumValues() {
        IngredientType[] types = IngredientType.values();

        assertEquals(2, types.length); // Должно быть 2 значения
        assertEquals("SAUCE", types[0].name());
        assertEquals("FILLING", types[1].name());
    }

    @Test
    void testSauceValue() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
        assertSame(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    void testFillingValue() {
        assertEquals("FILLING", IngredientType.FILLING.name());
        assertSame(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    void testValueOf() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    void testToString() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}
