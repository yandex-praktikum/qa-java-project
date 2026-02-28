package praktikum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        IngredientType[] types = IngredientType.values();

        assertEquals(2, types.length, "Должно быть 2 типа ингредиентов");
        assertTrue(Arrays.asList(types).contains(IngredientType.SAUCE));
        assertTrue(Arrays.asList(types).contains(IngredientType.FILLING));
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testEnumValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> IngredientType.valueOf("KETCHUP"));
    }
}