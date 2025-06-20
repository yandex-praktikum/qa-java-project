package UnitsTests;

import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testContainsEnumValues() {
        // Проверяем наличие значения SAUCE
        Assert.assertEquals("SAUCE", IngredientType.SAUCE.name());
        // Проверяем наличие значения FILLING
        assertEquals("FILLING", IngredientType.FILLING.name());
    }

    @Test
    public void testEnumValuesCount() {
        // Проверяем, что в enum ровно 2 значения
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void testEnumValues() {
        // Проверка, что enum содержит ожидаемые значения
        IngredientType[] values = IngredientType.values();
        assertTrue(contains(values, IngredientType.SAUCE));
        assertTrue(contains(values, IngredientType.FILLING));
    }

    private boolean contains(IngredientType[] array, IngredientType value) {
        for (IngredientType item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }
}